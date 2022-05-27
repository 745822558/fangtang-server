package com.lax.ftbot.service.impl;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.StringObject;
import com.github.unidbg.linux.android.dvm.VM;
import com.lax.ftbot.service.SignService;
import com.lax.ftbot.utils.vm.JDVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

    @Autowired
    JDVm jdVm;

    @Override
    public String getSign(String functionId, String uuid, String body) {
        VM vm = jdVm.getVm();
        AndroidEmulator emulator = jdVm.getEmulator();
        DvmClass cBitmapkitUtils = vm.resolveClass("com/jingdong/common/utils/BitmapkitUtils");
        StringObject ret = cBitmapkitUtils.callStaticJniMethodObject(emulator, "getSignFromJni()(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
                vm.resolveClass("android/content/Context").newObject(null),
                functionId,
                body,
                uuid,
                "android",
                "11.0.2");
        return ret.getValue();
    }
}
