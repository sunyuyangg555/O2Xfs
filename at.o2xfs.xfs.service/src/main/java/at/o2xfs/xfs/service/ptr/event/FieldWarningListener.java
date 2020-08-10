package at.o2xfs.xfs.service.ptr.event;

import at.o2xfs.xfs.v3_00.ptr.FieldFailure3;

public interface FieldWarningListener {
    void onFieldWarning(FieldFailure3 fieldFailure3);
}
