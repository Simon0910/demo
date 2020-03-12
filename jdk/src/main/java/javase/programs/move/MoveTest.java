package javase.programs.move;

import java.io.File;
import java.util.Scanner;

/**
 * 批量移动 某类后缀的文件
 *
 * @author lzp on 2020/3/12.
 */
public class MoveTest {
    public static void main(String[] args) {
        System.out.println("源目录");
        File source = getDir();
        System.out.println("目标目录");
        File destination = getDir();
        System.out.println("后缀名(需要带点哦)");
        String ext = getExt();

        // String sourceAbsolutePath = source.getAbsolutePath();
        // String destinationAbsolutePath = destination.getAbsolutePath();
        // System.out.println(destinationAbsolutePath.contains(sourceAbsolutePath));

        if (confirm()) {
            long startTime = System.currentTimeMillis();
            move(source, destination, ext);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) / 1000);
            System.out.println("ok");
        }
    }

    public static boolean confirm() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("确定吗?yes/no:");
            String line = sc.nextLine();
            if (line == null || "".equals(line.trim())) {
                continue;
            }
            if ("yes".equalsIgnoreCase(line.trim())) {
                return true;
            } else if ("no".equalsIgnoreCase(line.trim())) {
                return false;
            } else {
                continue;
            }
        }
    }

    public static String getExt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个带点的后缀:");
        while (true) {
            String line = sc.nextLine();
            if (line == null || "".equals(line.trim()) || !line.contains(".")) {
                System.out.println("请输入一个带点的后缀:");
            } else {
                return line;
            }
        }
    }

    public static File getDir() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个文件夹路径:");
        while (true) {
            String line = sc.nextLine();
            File dir = new File(line);
            if (!dir.exists())
                System.out.println("您录入的文件夹路径不存在,请输入一个文件夹路径:");
            else if (dir.isFile()) {
                System.out.println("您录入的是文件路径,请输入一个文件夹路径:");
            } else
                return dir;
        }
    }

    public static void move(File source, File destination, String ext) {
        if (!destination.isDirectory()) {
            System.out.println("目标不是一个文件夹: " + destination.getAbsolutePath());
            return;
        }
        if (ext == null || "".equals(ext.trim()) || !ext.contains(".")) {
            System.out.println("后缀不正确: " + ext);
            return;
        }
        String sourceAbsolutePath = source.getAbsolutePath();
        String destinationAbsolutePath = destination.getAbsolutePath();
        if (destinationAbsolutePath.contains(sourceAbsolutePath)) {
            System.out.println("目标路径不能是子目录");
            return;
        }

        File[] subFiles = source.listFiles();
        for (File subFile : subFiles) {
            if (subFile.getName().endsWith(ext)) {
                String replaceName =
                        subFile.getAbsolutePath()
                                .replace("E:\\xx\\高手进阶篇\\", "")
                                .replace("1-6\\", "")
                                .replace("7-29\\", "")
                                .replace("30-32\\", "")
                                .replace("33-58\\", "")
                                .replace("59-72\\", "")
                                .replace("73-81\\", "")
                                .replace("82-92\\", "")
                                .replace("93-109\\", "")
                                .replace("110-125\\", "")
                                .replace("126-136\\", "")
                                .replace("\\视频\\课程视频", "");
                subFile.renameTo(new File(destination, replaceName));
            }
            if (subFile.isDirectory()) {
                move(subFile, destination, ext);
            }
        }
    }
}
