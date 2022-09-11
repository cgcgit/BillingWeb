/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
 any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/

package com.comasw.generalClass;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author catuxa
 * @param <T>
 *
 */
public class SimpleHistoricRelation<T, S, U> extends BasicRelationType<T, S, U> {

	protected String HISTORIC_CANDIDATE_DATA_TABLE_ID = "form:accordionPanel"
			+ uiValues.getString("historicCandidateDataTableID");
	
	
	/**
	 * Selected data list
	 */
	protected List<T> selectedDataList;

	/**
	 * Filtered data for the selected list
	 */
	protected List<T> filteredSelectedDataList;

	

	/**
	 * Historic data list
	 */
	protected List<U> historicCandidateDataList;

	/**
	 * Filtered data list for main data
	 */
	protected List<U> filteredHistoricCandidateDataList;
	
	/**
	 * Selected historic candidate data	 
	 */
	
	protected U selectedHistoricCandidateData;

	/**
	 * Search date
	 */
	protected LocalDateTime searchDate;

	/**
	 * Flag for the historic criteria to show related data
	 */

	protected boolean historicRelatedDataCriteria;
	
	/**
	 * Flag for the historic criteria to search
	 */

	protected boolean historicSearchDataCriteria;
	
	/**
	 * Text to shown in the search data table title
	 */
	protected String searchDataTableTitle;
	

	// --------------------
	// GETTERS AND SETTERS
	// -------------------
	

	/**
	 * @return the selectedDataList
	 */
	public List<T> getSelectedDataList() {
		return selectedDataList;
	}

	/**
	 * @param selectedDataList the selectedDataList to set
	 */
	public void setSelectedDataList(List<T> selectedDataList) {
		this.selectedDataList = selectedDataList;
	}

	/**
	 * @return the filteredSelectedDataList
	 */
	public List<T> getFilteredSelectedDataList() {
		return filteredSelectedDataList;
	}

	/**
	 * @param filteredSelectedDataList the filteredSelectedDataList to set
	 */
	public void setFilteredSelectedDataList(List<T> filteredSelectedDataList) {
		this.filteredSelectedDataList = filteredSelectedDataList;
	}


	/**
	 * @return the searchDate
	 */
	public LocalDateTime getSearchDate() {
		return searchDate;
	}

	/**
	 * @param searchDate the searchDate to set
	 */
	public void setSearchDate(LocalDateTime searchDate) {
		this.searchDate = searchDate;
	}

	/**
	 * @return the historicCandidateDataList
	 */
	public List<U> getHistoricCandidateDataList() {
		return historicCandidateDataList;
	}

	/**
	 * @param historicCandidateDataList the historicCandidateDataList to set
	 */
	public void setHistoricCandidateDataList(List<U> historicCandidateDataList) {
		this.historicCandidateDataList = historicCandidateDataList;
	}

	/**
	 * @return the filteredHistoricCandidateDataList
	 */
	public List<U> getFilteredHistoricCandidateDataList() {
		return filteredHistoricCandidateDataList;
	}

	/**
	 * @param filteredHistoricCandidateDataList the
	 *                                          filteredHistoricCandidateDataList to
	 *                                          set
	 */
	public void setFilteredHistoricCandidateDataList(List<U> filteredHistoricCandidateDataList) {
		this.filteredHistoricCandidateDataList = filteredHistoricCandidateDataList;
	}

	
	/**
	 * @return the selectedHistoricCandidateData
	 */
	public U getSelectedHistoricCandidateData() {
		return selectedHistoricCandidateData;
	}

	/**
	 * @param selectedHistoricCandidateData the selectedHistoricCandidateData to set
	 */
	public void setSelectedHistoricCandidateData(U selectedHistoricCandidateData) {
		this.selectedHistoricCandidateData = selectedHistoricCandidateData;
	}
	
	/**
	 * @return the historicRelatedDataCriteria
	 */
	public boolean isHistoricRelatedDataCriteria() {
		return historicRelatedDataCriteria;
	}

	/**
	 * @param historicRelatedDataCriteria the historicRelatedDataCriteria to set
	 */
	public void setHistoricRelatedDataCriteria(boolean historicRelatedDataCriteria) {
		this.historicRelatedDataCriteria = historicRelatedDataCriteria;
	}
	
	/**
	 * @return the historicSearchDataCriteria
	 */
	public boolean isHistoricSearchDataCriteria() {
		return historicSearchDataCriteria;
	}

	/**
	 * @param historicSearchDataCriteria the historicSearchDataCriteria to set
	 */
	public void setHistoricSearchDataCriteria(boolean historicSearchDataCriteria) {
		this.historicSearchDataCriteria = historicSearchDataCriteria;
	}


	/**
	 * @return the searchDataTableTitle
	 */
	public String getSearchDataTableTitle() {
		return searchDataTableTitle;
	}

	/**
	 * @param searchDataTableTitle the searchDataTableTitle to set
	 */
	public void setSearchDataTableTitle(String searchDataTableTitle) {
		this.searchDataTableTitle = searchDataTableTitle;
	}

	


}
