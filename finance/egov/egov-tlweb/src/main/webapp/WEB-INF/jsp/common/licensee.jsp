<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~    accountability and the service delivery of the government  organizations.
  ~
  ~     Copyright (C) <2015>  eGovernments Foundation
  ~
  ~     The updated version of eGov suite of products as by eGovernments Foundation
  ~     is available at http://www.egovernments.org
  ~
  ~     This program is free software: you can redistribute it and/or modify
  ~     it under the terms of the GNU General Public License as published by
  ~     the Free Software Foundation, either version 3 of the License, or
  ~     any later version.
  ~
  ~     This program is distributed in the hope that it will be useful,
  ~     but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~     GNU General Public License for more details.
  ~
  ~     You should have received a copy of the GNU General Public License
  ~     along with this program. If not, see http://www.gnu.org/licenses/ or
  ~     http://www.gnu.org/licenses/gpl.html .
  ~
  ~     In addition to the terms of the GPL license to be adhered to in using this
  ~     program, the following additional terms are to be complied with:
  ~
  ~         1) All versions of this program, verbatim or modified must carry this
  ~            Legal Notice.
  ~
  ~         2) Any misrepresentation of the origin of the material is prohibited. It
  ~            is required that all modified versions of this material be marked in
  ~            reasonable ways as different from the original version.
  ~
  ~         3) This license does not grant any rights to any user of the program
  ~            with regards to rights under trademark law for use of the trade names
  ~            or trademarks of eGovernments Foundation.
  ~
  ~   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
  --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<div class="panel-heading custom_form_panel_heading">
	    <div class="panel-title"><s:text name='license.title.applicantdetails' /></div>
	</div>
	<div class="form-group">
	    <label class="col-sm-3 control-label text-right"><s:text name='licensee.aadhaarNo' /></label>
	    <div class="col-sm-3 add-margin">
	           <s:textfield name="licensee.uid" onBlur="return checkMinLength(this,12);"  onKeyPress="return numbersonly(this, event)"  maxlength="12" cssClass="form-control patternvalidation"  data-pattern="number" />
	    </div>
	    <label class="col-sm-2 control-label text-right"><s:text name='licensee.mobileNo' /><span class="mandatory"></span></label>
	    <div class="col-sm-3 add-margin">
	             <s:textfield name="licensee.mobilePhoneNumber" id="mobilePhoneNumber" onKeyPress="return numbersonly(this, event)" onBlur="checkMinLength(this,10)" maxlength="10" cssClass="form-control patternvalidation" data-pattern="number" />
	    </div>
	</div>
	<div class="form-group">
	    <label class="col-sm-3 control-label text-right"><s:text name='licensee.applicantname' /><span class="mandatory"></span></label>
	    <div class="col-sm-3 add-margin">
	       <s:textfield name="licensee.applicantName" id="applicantName" maxlength="32" cssClass="form-control patternvalidation" data-pattern="alphabetwithspace" />
	    </div>
	    <label class="col-sm-2 control-label text-right"><s:text name='licensee.father/spouse' /><span class="mandatory"></span></label>
	    <div class="col-sm-3 add-margin">
	      	<s:textfield name="licensee.fatherOrSpouseName" maxlength="32" id="fatherOrSpouseName" cssClass="form-control patternvalidation" data-pattern="alphabetwithspace" />
	    </div>
	</div>
	<div class="form-group">
	    <label class="col-sm-3 control-label text-right"><s:text name='licensee.emailId' /><span class="mandatory"></span></label>
	    <div class="col-sm-3 add-margin">
	        <s:textfield    name="licensee.emailId" id="emailId" onBlur="validateEmail(this);checkLength(this,32)" maxlength="32" cssClass="form-control" />
	    </div>
	    <label class="col-sm-2 control-label text-right"><s:text name='licensee.address' /><span class="mandatory"></span></label>
	    <div class="col-sm-3 add-margin">
	        <s:textarea name="licensee.address"  id="licenseeAddress" maxlength="250" cssClass="form-control" />
	    </div>
	</div>