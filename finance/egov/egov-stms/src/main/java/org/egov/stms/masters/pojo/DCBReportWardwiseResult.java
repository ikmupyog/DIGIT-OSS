/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
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
 */


package org.egov.stms.masters.pojo;

import static org.egov.search.domain.Filter.termsStringFilter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.egov.infra.admin.master.entity.Boundary;
import org.egov.search.domain.Filter;
import org.egov.search.domain.Filters;
import org.egov.stms.utils.constants.SewerageTaxConstants;
import org.jboss.logging.Logger;

public class DCBReportWardwiseResult {
    private static final Logger logger = Logger.getLogger(DCBReportWardwiseResult.class);
    private String searchText;
    private List<Boundary> wards;
    private Long wardId;
    private String propertyType;
    private String mode;
    
    private String ulbName;
    private String revenueWard;
    private String status;
    private String installmentYearDescription;
    private int noofassessments;
    private String shscnumber;
    private String applicationNumber;
    private String ownerName;
    
    private BigDecimal curr_demand=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal arr_demand=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal curr_collection=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal arr_collection=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal curr_balance=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal arr_balance=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal total_collection=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal total_demand=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal total_balance=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    
    public List<Boundary> getWards() {
        return wards;
    }
    public void setWards(List<Boundary> wards) {
        this.wards = wards;
    }
    public String getPropertyType() {
        return propertyType;
    }
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public BigDecimal getCurr_demand() {
        return curr_demand;
    }
    public void setCurr_demand(BigDecimal curr_demand) {
        this.curr_demand = curr_demand;
    }
    public BigDecimal getArr_demand() {
        return arr_demand;
    }
    public void setArr_demand(BigDecimal arr_demand) {
        this.arr_demand = arr_demand;
    }
    public BigDecimal getCurr_collection() {
        return curr_collection;
    }
    public void setCurr_collection(BigDecimal curr_collection) {
        this.curr_collection = curr_collection;
    }
    public BigDecimal getArr_collection() {
        return arr_collection;
    }
    public void setArr_collection(BigDecimal arr_collection) {
        this.arr_collection = arr_collection;
    }
    public BigDecimal getCurr_balance() {
        return curr_balance;
    }
    public void setCurr_balance(BigDecimal curr_balance) {
        this.curr_balance = curr_balance;
    }
    public BigDecimal getArr_balance() {
        return arr_balance;
    }
    public void setArr_balance(BigDecimal arr_balance) {
        this.arr_balance = arr_balance;
    }
    public String searchQuery() {
        return searchText;
    }
    public Filters searchFilters() { 
        final List<Filter> andFilters = new ArrayList<>(0);
        if(propertyType!=null && !propertyType.equalsIgnoreCase("ALL")){
        andFilters.add(termsStringFilter(SewerageTaxConstants.CLAUSES_PROPERTY_TYPE, propertyType));
        }
        if(revenueWard!=null){
        andFilters.add(termsStringFilter(SewerageTaxConstants.CLAUSES_REVWARD_NAME, revenueWard));
        }
        andFilters.add(termsStringFilter(SewerageTaxConstants.CLAUSES_ISACTIVE,"true"));
        if (logger.isDebugEnabled())
            logger.debug("finished filters");
        return Filters.withAndFilters(andFilters);
    }
    
    public String getSearchText() {
        return searchText;
    }
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    public String getUlbName() {
        return ulbName;
    }
    public void setUlbName(String ulbName) {
        this.ulbName = ulbName;
    }
    public String getRevenueWard() {
        return revenueWard;
    }
    public void setRevenueWard(String revenueWard) {
        this.revenueWard = revenueWard;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getInstallmentYearDescription() {
        return installmentYearDescription;
    }
    public void setInstallmentYearDescription(String installmentYearDescription) {
        this.installmentYearDescription = installmentYearDescription;
    }
    public int getNoofassessments() {
        return noofassessments;
    }
    public void setNoofassessments(int noofassessments) {
        this.noofassessments = noofassessments;
    }
    public BigDecimal getTotal_collection() {
        return total_collection;
    }
    public void setTotal_collection(BigDecimal total_collection) {
        this.total_collection = total_collection;
    }
    public BigDecimal getTotal_demand() {
        return total_demand;
    }
    public void setTotal_demand(BigDecimal total_demand) {
        this.total_demand = total_demand;
    }
    public BigDecimal getTotal_balance() {
        return total_balance;
    }
    public void setTotal_balance(BigDecimal total_balance) {
        this.total_balance = total_balance;
    }
    public Long getWardId() {
        return wardId;
    }
    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }
    public String getShscnumber() {
        return shscnumber;
    }
    public void setShscnumber(String shscnumber) {
        this.shscnumber = shscnumber;
    }
    public String getApplicationNumber() {
        return applicationNumber;
    }
    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }
    
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}
