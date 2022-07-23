package com.comasw.generalClass;

import java.util.List;

public class RelationBasicTypeWithList<T, S, U> extends BasicTypeWithLists<T> {


	protected String DATA_TABLE_ID = "form:accordionPanel:"	+ uiValues.getString("dataTableID");
	
	protected String RELATED_DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("relatedDataTableID");

	protected String CANDIDATE_DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("candidateDataTableID");

	/**
	 * Indicates if the data form must be shown
	 */
	protected boolean showDependentData = false;
	
	
	/**
	 * Related data list
	 */
	protected List<S> relatedDataList;

	/**
	 * Filtered data list for related data
	 */
	protected List<S> filteredRelatedDataList;

	/**
	 * Candidate data list
	 */
	protected List<U> candidateDataList;

	/**
	 * Filtered data list for candidate data
	 */
	protected List<U> filteredCandidateDataList;

	/**
	 * Selected related data 
	 */
	protected S selectedRelatedData;
	
	
	/**
	 * Selected candidat data
	 */
	protected U selectedCandidateData;
	
	

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	

	/**
	 * @return the relatedDataList
	 */	
	public List<S> getRelatedDataList() {
		return relatedDataList;
	}


	/**
	 * @param relatedDataList the relatedDataList to set
	 */
	public void setRelatedDataList(List<S> relatedDataList) {
		this.relatedDataList = relatedDataList;
	}

	/**
	 * @return the filteredRelatedDataList
	 */
	public List<S> getFilteredRelatedDataList() {
		return filteredRelatedDataList;
	}

	/**
	 * @param filteredRelatedDataList the filteredRelatedDataList to set
	 */
	public void setFilteredRelatedDataList(List<S> filteredRelatedDataList) {
		this.filteredRelatedDataList = filteredRelatedDataList;
	}

	/**
	 * @return the candidateDataList
	 */
	public List<U> getCandidateDataList() {
		return candidateDataList;
	}

	/**
	 * @param candidateDataList the candidateDataList to set
	 */
	public void setCandidateDataList(List<U> candidateDataList) {
		this.candidateDataList = candidateDataList;
	}

	/**
	 * @return the filteredCandidateDataList
	 */
	public List<U> getFilteredCandidateDataList() {
		return filteredCandidateDataList;
	}

	/**
	 * @param filteredCandidateDataList the filteredCandidateDataList to set
	 */
	public void setFilteredCandidateDataList(List<U> filteredCandidateDataList) {
		this.filteredCandidateDataList = filteredCandidateDataList;
	}

	/**
	 * @return the showDependentData
	 */
	public boolean isShowDependentData() {
		return showDependentData;
	}

	/**
	 * @param showDependentData the showDependentData to set
	 */
	public void setShowDependentData(boolean showDependentData) {
		this.showDependentData = showDependentData;
	}

	

	/**
	 * @return the selectedRelatedData
	 */
	public S getSelectedRelatedData() {
		return selectedRelatedData;
	}


	/**
	 * @param selectedRelatedData the selectedRelatedDataList to set
	 */
	public void setSelectedRelatedData(S selectedRelatedData) {
		this.selectedRelatedData = selectedRelatedData;
	}


	/**
	 * @return the selectedCandidateData
	 */
	public U getSelectedCandidateData() {
		return selectedCandidateData;
	}


	/**
	 * @param selectedCandidateData the selectedCandidateData to set
	 */
	public void setSelectedCandidateData(U selectedCandidateData) {
		this.selectedCandidateData = selectedCandidateData;
	}
	
	
}
