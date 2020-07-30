package at.o2xfs.operator.task.xfs;


import at.o2xfs.operator.task.xfs.cim.*;

import at.o2xfs.xfs.*;
import at.o2xfs.xfs.cim.*;

import at.o2xfs.xfs.service.cdm.CdmService;
import at.o2xfs.xfs.service.cdm.CdmServiceListener;

import at.o2xfs.xfs.service.cim.CimService;

import at.o2xfs.xfs.service.cim.execute.*;
import at.o2xfs.xfs.service.cim.info.*;
import at.o2xfs.xfs.service.cmd.event.CancelEvent;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.ErrorEvent;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.v3_00.cdm.CashUnit3;

import at.o2xfs.xfs.v3_00.cim.*;
import at.o2xfs.xfs.v3_10.cim.GetItemInfo310;
import at.o2xfs.xfs.v3_10.cim.ItemInfo310;
import at.o2xfs.xfs.v3_10.cim.PositionCapabilities310;
import at.o2xfs.xfs.v3_30.cdm.IncompleteRetract330;
import at.o2xfs.xfs.v3_30.cdm.ItemInfoSummary330;
import at.o2xfs.xfs.win32.XfsWord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class XFS {

    public static class CIM {

        /**
         * This command is used to obtain the status of the CIM. It may also return vendor-specific status
         * information.
         *
         * @param service
         * @return
         */
        public static Mono<Status3> status(CimService service) {
            return
                    Mono.create(monoSink -> {
                        try {
                            Status3 status = new CimStatusCommand(service).call();
                            monoSink.success(status);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });

        }

        /**
         * This command is used to retrieve the capabilities of the cash acceptor.
         *
         * @param service
         * @return
         */
        public static Mono<Capabilities3> capabilities(CimService service) {
            return
                    Mono.create(monoSink -> {
                        try {
                            Capabilities3 capabilities = new CimCapabilitiesCommand(service).call();
                            monoSink.success(capabilities);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<CashInfo3> cashUnitInfo(CimService service) {
            return
                    Mono.create(monoSink -> {
                        try {
                            CashInfo3 cashInfo3 = new CashUnitInfoCommand(null,service).call();
                            monoSink.success(cashInfo3);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<List<TellerDetails3>> tellerInfo(CimService service) {
            return
                    Mono.create(tellerInfo3MonoSink -> {
                        TellerInfo3 tellerInfo3 = new TellerInfo3.Builder(0, "CNY".toCharArray()).build();
                        try {
                            List<TellerDetails3> tellerDetailsList = new TellerInfoCommand(service, tellerInfo3).call();
                            tellerInfo3MonoSink.success(tellerDetailsList);
                        } catch (XfsException e) {
                            tellerInfo3MonoSink.error(e);
                        }
                    });
        }

        public static Mono<CurrencyExp3> currentExp(CimService cimService) {
            return
                    Mono.create(monoSink -> {
                        try {
                            CurrencyExp3 currencyExp3 = new CurrencyExpCommand(cimService).call();
                            monoSink.success(currencyExp3);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<NoteTypeList3> banknoteTypes(CimService cimService) {
            return
                    Mono.create(monoSink -> {
                        try {
                            NoteTypeList3 noteTypeList = new BanknoteTypesCommand(cimService).call();
                            monoSink.success(noteTypeList);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<CashInStatus3> cashInStatus(CimService service) {
            CashInStatusCommand cashInStatusCommand = new CashInStatusCommand(service);
            service.removeAll();
            return
                    Mono.create(monoSink -> {
                        try {
                            CashInStatus3 cashInStatus3 = cashInStatusCommand.call();
                            monoSink.success(cashInStatus3);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<List<P6Info3>> getP6Info(CimService cimService) {
            return
                    Mono.create(monoSink -> {
                        try {
                            List<P6Info3> p6Info3s = new GetP6InfoCommand(cimService).call();
                            monoSink.success(p6Info3s);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<P6Signature3> getP6Signature(CimService cimService) {
            return
                    Mono.create(monoSink -> {
                        try {
                            GetP6Signature3 getP6Signature3 = new GetP6Signature3.Builder(Level.ALL, 0).build();
                            P6Signature3 p6Signature = new GetP6SignatureCommand(cimService, getP6Signature3).call();
                            monoSink.success(p6Signature);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<ItemInfo310> getItemInfo(CimService cimService) {
            return
                    Mono.create(monoSink -> {
                        try {
                            GetItemInfo310 getItemInfo310 = new GetItemInfo310.Builder(Level.ALL, 0).build();
                            ItemInfo310 itemInfo310 = new GetItemInfoCommand(cimService, getItemInfo310).call();
                            monoSink.success(itemInfo310);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Mono<PositionCapabilities310> positionCapabilities(CimService cimService) {
            return
                    Mono.create(monoSink -> {
                        try {
                            PositionCapabilities310 positionCapabilities310 = new PositionCapabilitiesCommand(cimService).call();
                            monoSink.success(positionCapabilities310);
                        } catch (XfsException e) {
                            monoSink.error(e);
                        }
                    });
        }

        public static Flux openShutter(CimService service) {

            XfsWord<Position> position = new XfsWord<>(Position.class);
            position.allocate();
            position.set(Position.NULL);
            OpenShutterCommand openShutterCommand = new OpenShutterCommand(service, null);
            service.removeAll();

            return
                    Flux.create(fluxSink -> {

                        /*service.add(new CimUserAdapter(fluxSink));
                        openShutterCommand.addCommandListener(new CommandAdapter(fluxSink));
                        service.add(new CimServiceAdapter(fluxSink));
                        openShutterCommand.execute();*/
                    })
                            .doFinally(signalType -> openShutterCommand.removeCommandListener());
        }

        public static Flux closeShutter(CimService service) {

            XfsWord<Position> position = new XfsWord<>(Position.class);
            position.allocate();
            position.set(Position.NULL);
            CloseShutterCommand closeShutterCommand = new CloseShutterCommand(service, null);

            return
                    Flux.create(fluxSink -> {

                        closeShutterCommand.addCommandListener(new CommandListener<SuccessEvent>() {
                            @Override
                            public void onCancel(CancelEvent event) {
                                fluxSink.error(XfsException.createFor(XfsError.CANCELED.getValue()));
                            }

                            @Override
                            public void onError(ErrorEvent event) {
                                fluxSink.error(XfsException.createFor(XfsError.SOFTWARE_ERROR.getValue()));
                            }

                            @Override
                            public void onComplete(SuccessEvent event) {
                                fluxSink.complete();
                            }
                        });
                        closeShutterCommand.execute();
                    })
                            .doFinally(signalType -> closeShutterCommand.removeCommandListener());
        }

        public static Flux cashInStart(CimService service) {
            CashInStartCommand cashInStartCommand = new CashInStartCommand(service, new CashInStart3.Builder().build());
            return
                    Flux.create(sink -> {
                        cashInStartCommand.addCommandListener(new CashInStartAdapter(sink));
                        cashInStartCommand.execute();
                    })
                            .doFinally(signalType -> cashInStartCommand.removeCommandListener());
        }

        public static Flux cashInEnd(CimService service) {
            CashInEndCommand cashInEndCommand = new CashInEndCommand(service);
            service.removeAll();
            return
                    Flux.create(sink -> {
                        CashInEndListener cashInEndListener = new CashInEndAdapter(sink);
                        cashInEndCommand.addCommandListener(cashInEndListener);

                        /*service.add(new CimServiceAdapter(sink));

                        service.add(cashUnit -> {
                            Map map = new BeanMap(cashUnit);
                            sink.next(map);
                        });*/
                        cashInEndCommand.execute();
                    })
                            .doFinally(signalType -> {
                                cashInEndCommand.removeCommandListener();
                            });
        }

        public static Flux cashIn(CimService service) {
            CashInCommand cashInCommand = new CashInCommand(service);
            service.removeAll();
            return
                    Flux.create(fluxSink -> {

                        /*cashInCommand.addCommandListener(new CashInAdapter(fluxSink));
                        service.add(new CimServiceAdapter(fluxSink));
                        cashInCommand.execute();*/
                    })
                            .doFinally(signalType -> cashInCommand.removeCommandListener());
        }

        public static Flux cashInRollBack(CimService service) {
            CashInRollbackCommand cashInRollbackCommand = new CashInRollbackCommand(service);
            service.removeAll();
            return Flux.create(sink -> {

                cashInRollbackCommand.addCommandListener(new CashInRollbackAdapter(sink));
                /*service.add(new CimServiceAdapter(sink));*/
                cashInRollbackCommand.execute();
            })
                    .doFinally(signalType -> cashInRollbackCommand.removeCommandListener());
        }

        public static Flux retract(CimService cimService) {
            RetractCommand retractCommand = new RetractCommand(cimService, new Retract3.Builder(Position.NULL, RetractArea.RA_RETRACT, 1).build());
            cimService.removeAll();
            return
                    Flux.create(fluxSink -> {
                        retractCommand.addCommandListener(new RetractAdapter(fluxSink));
                        /*cimService.add(new CimServiceAdapter(fluxSink));
                        cimService.add(new CimUserAdapter(fluxSink));*/
                        retractCommand.execute();
                    })
                            .doFinally(signalType -> retractCommand.removeCommandListener());
        }

        /**
         * This command is used to adjust information about the status and contents of the cash units present
         * in the CIM.
         * This command generates the service event WFS_SRVE_CIM_CASHUNITINFOCHANGED to
         * inform applications that cash unit information has been changed.
         * This command can only be used to change software counters, thresholds and the application lock.
         * All other fields in the input structure will be ignored.
         * The following fields of the WFSCIMCASHIN structure may be updated by this command:
         * ulCount
         * ulCashInCount
         * ulMaximum
         * bAppLock
         * lpNoteNumberList (contents must be consistent with ulCount )
         * ulInitialCount
         * ulDispensedCount
         * ulPresentedCount
         * ulRetractedCount
         * ulRejectCount
         * ulMinimum
         * As may the following fields of the WFSCIMPHCU structure:
         * ulCashInCount
         * ulCount
         * ulInitialCount
         * ulDispensedCount
         * ulPresentedCount
         * ulRetractedCount
         * ulRejectCount
         * Any other changes must be performed via an exchange operation.
         * The lppPhysical counts must be consistent with the logical cash unit counts. The Service Provider
         * controls whether the logical counts are maintained separately or are based on the sum of the
         * physical counts.
         * If the fields ulCount and ulCashInCount of lppPhysical are set to zero by this command, the
         * application is indicating that it does not wish counts to be maintained for the physical cash units.
         * Counts on the logical cash units will still be maintained and can be used by the application. If the
         * physical counts are set by this command then the logical count will be the sum of the physical
         * counts and any value sent as a logical count will be ignored.
         *
         * @param cimService
         * @param cashInfo3  The LPWFSCIMCASHINFO structure is specified in the documentation of the
         *                   WFS_INF_CIM_CASH_UNIT_INFO command. All cash units must be included not just the
         *                   cash units whose values are to be changed.
         * @return
         */
        public static Flux setCashUnitInfo(CimService cimService, CashInfo3 cashInfo3) {
            SetCashUnitInfoCommand setCashUnitInfoCommand = new SetCashUnitInfoCommand(cimService, cashInfo3);
            cimService.removeAll();
            return
                    Flux.create(fluxSink -> {
                        setCashUnitInfoCommand.addCommandListener(new SetCashUnitInfoAdapter(fluxSink));
                        /*cimService.add(new CimServiceAdapter(fluxSink));
                        cimService.add(new CimUserAdapter(fluxSink));*/
                        setCashUnitInfoCommand.execute();
                    })
                            .doFinally(signalType -> setCashUnitInfoCommand.removeCommandListener());
        }

        public static Flux startExchange(CimService service, StartEx3 startEx3) {
            service.removeAll();
            StartExchangeCommand startExchangeCommand = new StartExchangeCommand(service, startEx3);
            return
                    Flux.create(fluxSink -> {
                        startExchangeCommand.addCommandListener(new StartExchangeAdapter(fluxSink));
                        /*service.add(new CimServiceAdapter(fluxSink));
                        service.add(new CimUserAdapter(fluxSink));*/
                        startExchangeCommand.execute();
                    })
                            .doFinally(signalType -> startExchangeCommand.removeCommandListener());

        }

        public static Flux endExchange(CimService service, CashInfo3 cashInfo3) {
            EndExchangeCommand endExchangeCommand = new EndExchangeCommand(service, null);
            service.removeAll();
            return
                    Flux.create(fluxSink -> {
                        endExchangeCommand.addCommandListener(new EndExchangeAdapter(fluxSink));
                        /*service.add(new CimServiceAdapter(fluxSink));
                        service.add(new CimUserAdapter(fluxSink));*/
                        endExchangeCommand.execute();
                    })
                            .doFinally(signalType -> endExchangeCommand.removeCommandListener());
        }

    }

    public static class CDM {

        /*public static Flux reset(CdmService service) {

            ResetCommand resetCommand = new ResetCommand(service);
            service.removeServiceListeners();
            return
                    Flux.create(fluxSink -> {
                        resetCommand.addCommandListener(new ResetListener() {
                            @Override
                            public void onIncompleteRetract(IncompleteRetract330 incompleteRetract) {

                            }

                            @Override
                            public void onCashUnitError(at.o2xfs.xfs.v3_00.cdm.CashUnitError3 cashUnitError) {

                            }

                            @Override
                            public void onInfoAvailable(ItemInfoSummary330 itemInfoSummary) {

                            }

                            @Override
                            public void onInputP6() {

                            }

                            @Override
                            public void onCancel(CancelEvent event) {

                            }

                            @Override
                            public void onError(ErrorEvent event) {

                            }

                            @Override
                            public void onComplete(SuccessEvent event) {

                            }
                        });
                        service.addServiceListener(new CdmServiceListener() {
                            @Override
                            public void onSafeDoorOpen() {

                            }

                            @Override
                            public void onSafeDoorClosed() {

                            }

                            @Override
                            public void onCashUnitThreshold(CashUnit3 cashUnit) {

                            }

                            @Override
                            public void onCashUnitInfoChanged(CashUnit3 cashUnit) {

                            }

                            @Override
                            public void onTellerInfoChanged(int tellerId) {

                            }

                            @Override
                            public void onItemsTaken(at.o2xfs.xfs.cdm.Position position) {

                            }

                            @Override
                            public void onCountsChanged(at.o2xfs.xfs.v3_00.cdm.CountsChanged3 countsChanged) {

                            }

                            @Override
                            public void onItemsPresented() {

                            }

                            @Override
                            public void onMediaDetected(Optional<at.o2xfs.xfs.v3_00.cdm.ItemPosition3> itemPosition) {

                            }

                            @Override
                            public void onDevicePosition(at.o2xfs.xfs.v3_10.cdm.DevicePosition310 devicePosition) {

                            }

                            @Override
                            public void onPowerSaveChange(at.o2xfs.xfs.v3_10.cdm.PowerSaveChange310 powerSaveChange) {

                            }

                            @Override
                            public void onShutterStatusChanged(at.o2xfs.xfs.v3_30.cdm.ShutterStatusChanged330 shutterStatusChanged) {

                            }
                        });
                        resetCommand.execute();
                    })
                            .doFinally(signalType -> resetCommand.removeCommandListener());
        }*/
    }

}
