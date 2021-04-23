package com.example.demo.util.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadUtil {

    /**
     * 下载图片到本地
     *
     * @throws Exception
     */
    public static File download(String objectUrl) throws Exception {
        // 构造URL
//        URL url = new URL("http://wx.qlogo.cn/mmopen/vI3jvHjNcV5b4iaGiblRRfXVkwjLLHb2LfOja4PiaDbtGicYicXVAkTDRsxSEQxPmegIicJcTD3Vjia5V8m3vGwa0RKZSw5LtDAwtl8/0");
        URL url = new URL(objectUrl);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
//        File sf = new File(savePath);
//        if (!sf.exists()) {
//            sf.mkdirs();
//        }
        File sf = File.createTempFile("weifen", ".tmp");
        sf.deleteOnExit();

        OutputStream os = new FileOutputStream(sf.getPath());
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();

        return sf;
    }
}
