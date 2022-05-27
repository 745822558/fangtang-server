package com.lax.ftbot.service.impl;

import com.lax.ftbot.service.PythonService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ClassName PythonServiceImpl
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/4/29 14:20
 **/
@Service
public class PythonServiceImpl implements PythonService {
    @Override
    public String getWskey(String wskey) throws Exception {

        String[] args1 = new String[]{"python", "C:\\Users\\Administrator\\Desktop\\ProtobufBot-master\\src\\main\\java\\com\\lax\\service\\impl\\wskey.py", wskey};
        Process pcs = Runtime.getRuntime().exec(args1);
        pcs.waitFor();
        BufferedReader in = new BufferedReader(new InputStreamReader(pcs.getInputStream(), "GB2312"));
        String line = null;
//        System.out.println(in.readLine());
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            String[] s = line.split("\t");
//            System.out.println(s[0]+s[1]);
        }
//        System.out.println(in.readLine());
        if (in.readLine() == null) {
            System.out.println("yes hhhhhh");
        }
        return line;
    }
}
