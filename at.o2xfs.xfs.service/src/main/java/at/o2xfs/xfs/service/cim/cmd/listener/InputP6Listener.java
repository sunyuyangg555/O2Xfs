package at.o2xfs.xfs.service.cim.cmd.listener;

import at.o2xfs.xfs.v3_00.cim.P6Info3;

import java.util.List;

public interface InputP6Listener {
    void onInputP6(List<P6Info3> p6Info3s);
}
