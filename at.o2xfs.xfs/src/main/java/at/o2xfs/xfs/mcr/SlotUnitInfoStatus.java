package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;

public enum SlotUnitInfoStatus implements XfsConstant {

    /**
     * 存储槽存有封包，且扫描到二维码
     */
    NOTPRESENT(0L),

    /**
     * 存储槽存无封包
     */
    PRESENT(1L),
    /**
     * 存储槽有封包，未扫描到二维码
     */
    PRESENT_NOQRCODE(2L),
    /**
     * 存储槽不可用
     */
    UNUSABLE(3L);

    private final long value;

    SlotUnitInfoStatus(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
