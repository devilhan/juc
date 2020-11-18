package com.ebupt.crbt.zj.operator.utils;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

//���̴߳��ļ�����
public class ThreadFileDownload {

    public static class DownLoadProcessor {

        //�ļ���Դ·��
        private String source;

        //Ŀ��·��
        private String target;

        //ÿ���̶߳�ȡ�ֽڳ���
        private Long eachSize;

        //��ȡ�ļ��ܴ�С
        private Long totalLength;

        //Դ�ļ�
        private File sourceFile;

        //Ŀ���ļ�
        private File targetFile;

        //��������
        private int parallelism = 1000;

        public DownLoadProcessor(String source, String target) {
            this.source = source;
            this.target = target;
        }

        public void start() throws IOException {
            sourceFile = new File(source);
            targetFile = new File(target);
            totalLength = sourceFile.length();
            RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
            //raf.setLength(totalLength);
            raf.close();

            eachSize = totalLength / parallelism;
            CompletableFuture[] completableFutures = IntStream.range(0, parallelism).boxed().map(i -> CompletableFuture
                    .runAsync(() -> download(i))
                    .whenComplete((result, e) -> System.out.println(i + "-> over..."))).toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(completableFutures).join();
        }

        private void download(Integer index) {
            System.out.println(index);

            try (FileInputStream is = new FileInputStream(sourceFile);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 RandomAccessFile accessFile = new RandomAccessFile(targetFile, "rw")) {

                //ÿ�ζ�ȡ1024
                byte[] buff = new byte[1024];
                //todo ���Ż�

                //��ȡ��ǰ�̶߳�ȡ����,���һ����ȡʣ�ಿ��
                int start = (int) (index * eachSize);
                int end = (int) (index == parallelism - 1 ? totalLength - 1 : (index + 1) * eachSize - 1);
                int length = end - start + 1;
                int readLength = 0;
                is.skip(start);
                int len;
                //�����ļ���д�뱾��
                while ((len = is.read(buff)) != -1 && readLength <= length) {
                    baos.write(buff, 0, len);
                    readLength += len;
                }
                byte[] readData = baos.toByteArray();
                byte[] result = baos.size() > length ? Arrays.copyOf(readData, length) : readData;
                System.out.println(result.length);
                accessFile.seek(start);
                accessFile.write(result);
                System.out.println(start + "-" + end + " over.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
