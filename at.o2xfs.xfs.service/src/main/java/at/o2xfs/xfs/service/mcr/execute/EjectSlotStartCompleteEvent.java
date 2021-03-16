package at.o2xfs.xfs.service.mcr.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_10.mcr.EjectSlotOut310;

import java.util.Objects;
import java.util.Optional;

public class EjectSlotStartCompleteEvent implements CompleteEvent<Optional<EjectSlotOut310>> {

    private final Optional<EjectSlotOut310> ejectSlotOut;

    private EjectSlotStartCompleteEvent(Optional<EjectSlotOut310> ejectSlotOut) {
        Objects.requireNonNull(ejectSlotOut);
        this.ejectSlotOut = ejectSlotOut;
    }

    public static EjectSlotStartCompleteEvent build(Optional<EjectSlotOut310> ejectSlotOut) {
        return new EjectSlotStartCompleteEvent(ejectSlotOut);
    }
    @Override
    public Optional<EjectSlotOut310> get() {
        return ejectSlotOut;
    }
}
