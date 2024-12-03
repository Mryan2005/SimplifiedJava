package top.mryan2005;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RAFile {
    public ArrayList<String> content;
    public RAFile(String path) throws IOException {
        RandomAccessFile rf = new RandomAccessFile(path, "r");
        content = new ArrayList<>();
        while(rf.getFilePointer() < rf.length()) {
            content.add(rf.readLine());
        }
        rf.close();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for(String s : content) {
            res.append(s).append("\n");
        }
        return res.toString();
    }
}
