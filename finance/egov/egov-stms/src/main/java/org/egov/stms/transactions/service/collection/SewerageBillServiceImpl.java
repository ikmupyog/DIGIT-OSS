/*
 *    eGov  SmartCity eGovernance suite aims to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) 2017  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *            Further, all user interfaces, including but not limited to citizen facing interfaces,
 *            Urban Local Bodies interfaces, dashboards, mobile applications, of the program and any
 *            derived works should carry eGovernments Foundation logo on the top right corner.
 *
 *            For the logo, please refer http://egovernments.org/html/logo/egov_logo.png.
 *            For any further queries on attribution, including queries on brand guidelines,
 *            please contact contact@egovernments.org
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 *
 */

package org.egov.stms.transactions.service.collection;

import org.egov.collection.integration.models.BillAccountDetails.PURPOSE;
import org.egov.commons.Installment;
import org.egov.demand.interfaces.BillServiceInterface;
import org.egov.demand.interfaces.Billable;
import org.egov.demand.model.EgBillDetails;
import org.egov.demand.model.EgDemand;
import org.egov.demand.model.EgDemandDetails;
import org.egov.infra.admin.master.entity.AppConfigValues;
import org.egov.infra.admin.master.service.AppConfigValueService;
import org.egov.stms.utils.constants.SewerageTaxConstants;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SewerageBillServiceImpl extends BillServiceInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AppConfigValueService appConfigValuesService;

       public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<EgBillDetails> getBilldetails(final Billable billObj) {
        final List<EgBillDetails> billDetailList = new ArrayList<EgBillDetails>();
        int orderNo = 1;
        final SewerageBillable advBillable = (SewerageBillable) billObj;
        final EgDemand dmd = advBillable.getCurrentDemand();
        final List<EgDemandDetails> details = new ArrayList<EgDemandDetails>(dmd.getEgDemandDetails());

        if (!details.isEmpty())
            Collections.sort(details, (c1, c2) -> c1.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster()
                    .compareTo(c2.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster()));

        for (final EgDemandDetails demandDetail : details)
            if (demandDetail.getAmount().compareTo(BigDecimal.ZERO) > 0 && !demandDetail.getEgDemandReason().getEgDemandReasonMaster().getCode()
                    .equalsIgnoreCase(SewerageTaxConstants.FEES_ADVANCE_CODE)) {
                BigDecimal creaditAmt = demandDetail.getAmount().subtract(demandDetail.getAmtCollected());

                // If Amount- collected amount greather than zero, then send
                // these demand details to collection.
                if (creaditAmt.compareTo(BigDecimal.ZERO) > 0
                       ) {
       
                    final EgBillDetails billdetail = createBillDetailObject(orderNo, BigDecimal.ZERO, creaditAmt,
                                demandDetail.getEgDemandReason().getGlcodeId().getGlcode(), getReceiptDetailDescription(demandDetail.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster()
                                        +" "+SewerageTaxConstants.COLL_RECEIPTDETAIL_DESC_PREFIX,demandDetail.getEgDemandReason().getEgInstallmentMaster()));
                         orderNo++;
                        billDetailList.add(billdetail);
                }
            
                }
            return billDetailList;
    }   
    
    public List<EgBillDetails> getSewerageTaxTypeBilldetails(final Billable billObj) {
        final List<EgBillDetails> billDetailList = new ArrayList<EgBillDetails>();
        int orderNo = 1;
        final SewerageBillable advBillable = (SewerageBillable) billObj;
        final EgDemand dmd = advBillable.getCurrentDemand();
        final List<EgDemandDetails> details = new ArrayList<EgDemandDetails>(dmd.getEgDemandDetails());

        if (!details.isEmpty())
            Collections.sort(details, (c1, c2) -> c1.getEgDemandReason().getEgInstallmentMaster().getFromDate()
                    .compareTo(c2.getEgDemandReason().getEgInstallmentMaster().getFromDate()));

        for (final EgDemandDetails demandDetail : details)
            if (demandDetail.getAmount().compareTo(BigDecimal.ZERO) > 0 && demandDetail.getEgDemandReason().getEgDemandReasonMaster().getCode()
                    .equalsIgnoreCase(SewerageTaxConstants.FEES_SEWERAGETAX_CODE)) {
                BigDecimal creaditAmt = demandDetail.getAmount().subtract(demandDetail.getAmtCollected());

                // If Amount- collected amount greather than zero, then send
                // these demand details to collection.
                if (creaditAmt.compareTo(BigDecimal.ZERO) > 0
                       ) {
       
                    final EgBillDetails billdetail = createBillDetailObject(orderNo, BigDecimal.ZERO, creaditAmt,
                                demandDetail.getEgDemandReason().getGlcodeId().getGlcode(), getReceiptDetailDescription(demandDetail.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster()
                                        +" "+SewerageTaxConstants.COLL_RECEIPTDETAIL_DESC_PREFIX,demandDetail.getEgDemandReason().getEgInstallmentMaster()));
                    billdetail.setEgDemandReason(demandDetail.getEgDemandReason());    
                    orderNo++;
                        billDetailList.add(billdetail);
                }
            
                }
            return billDetailList;
    }  
    
    private String getReceiptDetailDescription(String reasonType, Installment instlment) {
             return reasonType+(instlment!=null? " "+instlment.getDescription():"");
         
        }
    private EgBillDetails createBillDetailObject(final int orderNo, final BigDecimal debitAmount,
            final BigDecimal creditAmount, final String glCodeForDemandDetail, final String description) {
      
        final AppConfigValues sewerageFunctionCode = appConfigValuesService.getConfigValuesByModuleAndKey(
                SewerageTaxConstants.MODULE_NAME, SewerageTaxConstants.SEWAREGE_FUCNTION_CODE).get(0);
        
        final EgBillDetails billdetail = new EgBillDetails();
        if(sewerageFunctionCode!=null)
            billdetail.setFunctionCode(sewerageFunctionCode.getValue());
        billdetail.setOrderNo(orderNo);
        billdetail.setCreateDate(new Date());
        billdetail.setModifiedDate(new Date());
        billdetail.setCrAmount(creditAmount);
        billdetail.setDrAmount(debitAmount);
        billdetail.setGlcode(glCodeForDemandDetail);
        billdetail.setDescription(description);
        billdetail.setAdditionalFlag(1);
        billdetail.setPurpose(PURPOSE.OTHERS.toString());
        return billdetail;
    }

    @Override
    public void cancelBill() {


    }

    @Override
    @Transactional
    public String getBillXML(final Billable billObj) {
        String collectXML;
        try {
            collectXML = URLEncoder.encode(super.getBillXML(billObj),"UTF-8");
            LOGGER.info("collectXML --------------------------> "+collectXML); 
        } catch (UnsupportedEncodingException e) {

            throw new RuntimeException(e.getMessage());
        }
        return collectXML;
    }

}
