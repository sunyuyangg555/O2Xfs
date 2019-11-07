package at.o2xfs.xfs.pin;

import at.o2xfs.xfs.XfsConstant;

public enum PINExportItemType implements XfsConstant {

    WFS_PIN_EXPORT_EPP_ID(0x0001),

    WFS_PIN_EXPORT_PUBLIC_KEY(0x0002);

    private final long value;

    PINExportItemType(long value) {
        this.value = value;
    }


    @Override
    public long getValue() {
        return value;
    }
}
