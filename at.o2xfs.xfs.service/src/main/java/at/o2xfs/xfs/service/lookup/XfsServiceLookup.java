/*
 * Copyright (c) 2017, Andreas Fagschlunger. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.o2xfs.xfs.service.lookup;

import at.o2xfs.xfs.XfsServiceClass;
import at.o2xfs.xfs.service.XfsService;
import at.o2xfs.xfs.service.bcr.BCRService;
import at.o2xfs.xfs.service.cam.CamService;
import at.o2xfs.xfs.service.cdm.CdmService;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.service.idc.IDCService;
import at.o2xfs.xfs.service.lookup.XfsServiceLookup.ServiceEntry;
import at.o2xfs.xfs.service.pin.PINService;
import at.o2xfs.xfs.service.ptr.PTRService;
import at.o2xfs.xfs.service.siu.SIUService;

public abstract class XfsServiceLookup implements Iterable<ServiceEntry> {

    public static class ServiceEntry {

        private final String logicalName;

        private final Class<? extends XfsService> servicClass;

        public ServiceEntry(String logicalName,
                            Class<? extends XfsService> servicClass) {
            this.logicalName = logicalName;
            this.servicClass = servicClass;
        }

        public String getLogicalName() {
            return logicalName;
        }

        public Class<? extends XfsService> getServicClass() {
            return servicClass;
        }
    }

    protected Class<? extends XfsService> getServicClass(XfsServiceClass serviceClass) {
        switch (serviceClass) {
            case PTR:
                return PTRService.class;
            case IDC:
                return IDCService.class;
            case PIN:
                return PINService.class;
            case CDM:
                return CdmService.class;
            case CIM:
                return CimService.class;
            case SIU:
                return SIUService.class;
            case BCR:
                return BCRService.class;
            case CAM:
                return CamService.class;
            default:
                throw new IllegalArgumentException(
                        "Unsupported XfsServiceClass: " + serviceClass);

        }
    }
}