package com.adverity.dw.repository;

import java.util.List;

public interface CampaignDataRepositoryCustom<T> {

    /**
     * retrieves the data based on a custom created sql query
     * @param sql the sql query
     * @return The list of data
     */
    List<T> fetchCampaignData(String sql);
}
