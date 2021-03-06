package com.lax.ftbot.utils.vm;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.VM;

public class JDVm {

    private AndroidEmulator emulator;
    private VM vm;

    public AndroidEmulator getEmulator() {
        return emulator;
    }

    public void setEmulator(AndroidEmulator emulator) {
        this.emulator = emulator;
    }

    public VM getVm() {
        return vm;
    }

    public void setVm(VM vm) {
        this.vm = vm;
    }
}
