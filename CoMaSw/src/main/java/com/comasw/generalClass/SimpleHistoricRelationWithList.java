/**
 * 
 */
package com.comasw.generalClass;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author catuxa
 * @param <T>
 *
 */
public class SimpleHistoricRelationWithList<T, S, U> extends RelationBasicTypeWithList<T, S, U> {

	protected String HISTORIC_CANDIDATE_DATA_TABLE_ID = "form:accordionPanel"
			+ uiValues.getString("historicCandidateDataTableID");


	
	/**
	 * Main data list
	 */
	protected List<U> historicCandidateDataList;

	/**
	 * Filtered data list for main data
	 */
	protected List<U> filteredHistoricCandidateDataList;
	

	/**
	 * Search date
	 */
	protected LocalDateTime searchDate;

	/**
	 * Flag for the historic criteria to show related data
	 */

	protected boolean historicRelatedDataCriteria;



	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	
	
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

	
	

}
