package com.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

@Service
public class DownloadJSONFileImpl implements DownloadJSONFile {
    public void getJSON(String source, String destinition) {
        URL url;
        try {
            url = new URL(source);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buf = new byte[5024];
            int n;
            while ((n = in.read(buf)) != -1) {
                out.write(buf, 0, n);
            }
            out.flush();
            out.close();
            in.close();

            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(destinition);
            fos.write(response);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
