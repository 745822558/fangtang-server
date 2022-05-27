package com.lax.ftbot.configuration;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.memory.Memory;
import com.lax.ftbot.utils.JDJni;
import com.lax.ftbot.utils.vm.JDVm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class JDConfiguration {

    public static String pkgName = "com.jingdong.app.mall";
    @Value("${apkpath}")
    public String apkPath;
    @Value("${sopath}")
    public String soPath;

    @Bean(name = "jdVm")
    public JDVm jdVm() throws IOException {
        JDVm jdVm = new JDVm();

        //AndroidEmulator emulator = AndroidEmulatorBuilder.for32Bit().setProcessName(pkgName).build();
        AndroidEmulator emulator = AndroidEmulatorBuilder.for64Bit().setProcessName(pkgName).build();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        VM vm = emulator.createDalvikVM(new File(apkPath));
        vm.setJni(new JDJni());
        jdVm.setEmulator(emulator);
        jdVm.setVm(vm);
        return jdVm;
    }

    @Bean("jdModule")
    public DalvikModule jdModule(@Qualifier("jdVm") JDVm jdVm) throws IOException {
        VM vm = jdVm.getVm();

        AndroidEmulator emulator = jdVm.getEmulator();
        DalvikModule dm = vm.loadLibrary(new File(soPath), false);
        vm.setVerbose(true);
        dm.callJNI_OnLoad(emulator);
        return dm;
    }

}
