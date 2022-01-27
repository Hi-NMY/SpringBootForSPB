package com.nmy.spb.service;

import com.nmy.spb.domain.dto.CollectBarDto;

/**
 * @author nmy
 * @title: CollectBarService
 * @date 2022-01-27 19:14
 */
public interface CollectBarService {

    String queryCollectBarList(String userAccount);

    String queryCollectBarFullList(String userAccount);

    String addCollectBar(CollectBarDto collectBarDto, String cacheAccount);

    String deleteCollectBar(CollectBarDto collectBarDto);

}
