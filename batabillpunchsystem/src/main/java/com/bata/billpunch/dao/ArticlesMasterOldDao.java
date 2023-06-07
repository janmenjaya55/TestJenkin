package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.ArticlesMasterOldModel;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;

@Repository
public interface ArticlesMasterOldDao extends JpaRepository<ArticlesMasterOldModel, Long> {
	
	public void save(List<ArticlesMasterOldModel> mn);

	@Modifying
	@Query(nativeQuery = true, value = "TRUNCATE TABLE tm_articles_master_dtls")
	void findWithDeleteAll();

	@Query(nativeQuery = true, value = "select a.* from  tm_articles_master_dtls a where a.ART_NO like ?1 ")
	public List<ArticlesMasterOldModel> findWithArticleDetails(String artno);
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select a.* from  tm_articles_master_dtls a where a.ART_NO like ?1 ") public
	 * ArticlesMasterOldModel findWithArticleDetailsByCode(String artcode);
	 */
	
	@Query(nativeQuery = true, value = "select a.* from  tm_old_articles_master_dtls a where a.ART_NO like ?1 ")
	public ArticlesMasterOldModel findWithArticleDetailsByCode(String artcode);
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select round(a.ART_PRICE_MRP,2) as artpricemrp ,round(a.ART_PRICE_FACTORY,2) as artpricefactory,round(a.ART_STND_COST ,2)  as artstdCost ,a.ART_SEQUENCE_NO as artseq from  tm_articles_master_dtls a where a.ART_NO like ?1 "
	 * ) public BillPunchResponseInterface findWithArticleDetailsByCodeReport(String
	 * artcode);
	 */
	
	@Query(nativeQuery = true, value = "select round(a.ART_PRICE_MRP,2) as artpricemrp ,round(a.ART_PRICE_FACTORY,2) as artpricefactory,round(a.ART_STND_COST ,2)  as artstdCost ,a.ART_SEQUENCE_NO as artseq from  tm_old_articles_master_dtls a where a.ART_NO like ?1 ")
	public BillPunchResponseInterface findWithArticleDetailsByCodeReport(String artcode);
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select distinct round(a.ART_PRICE_WSL*?2,2) as artpricewspedp ,round(a.ART_PRICE_MRP*?2,2) as artpricemrpedp,round(a.ART_PRICE_FACTORY*?2 ,2)  as artpricefactoryedp ,round(a.art_stnd_cost*?2 ,2)  as artstdCostedp from  tm_articles_master_dtls a where a.ART_NO like ?1 "
	 * ) public BillPunchResponseInterface
	 * findWithArticleDetailsByCodeReportNew(String artcode,String pair);
	 */
	@Query(nativeQuery = true, value = "select distinct round(a.ART_PRICE_WSL*?2,2) as artpricewspedp ,round(a.ART_PRICE_MRP*?2,2) as artpricemrpedp,round(a.ART_PRICE_FACTORY*?2 ,2)  as artpricefactoryedp ,round(a.art_stnd_cost*?2 ,2)  as artstdCostedp from  tm_old_articles_master_dtls a where a.ART_NO like ?1 ")
	public BillPunchResponseInterface findWithArticleDetailsByCodeReportNew(String artcode,String pair);
	
	/*
	@Query(nativeQuery = true, value = "select  round(sum(b.ART_PRICE_WSL*a.no_pairs) ,2)*100 as artpricewspedp,round(sum(b.ART_PRICE_MRP*a.no_pairs) ,2)*100 as artpricemrpedp ,round(sum(b.ART_PRICE_FACTORY*a.no_pairs) ,2)*100 as artpricefactoryedp,round(sum(b.art_stnd_cost*a.no_pairs)  ,2)*100  as artstdCostedp from  tt_bill_punch_dtls_one a,tm_articles_master_dtls b where b.art_no=a.art_code and a.rcpt_inv_no like ?1 and a.party_code like ?2 and a.GRNO like ?3 and a.status='CLOSED'")
	public BillPunchResponseInterface findWithArticleDetailsEdpReport(String invno,String partyCode,String grno);
	
	@Query(nativeQuery = true, value = "select  round(sum(b.ART_PRICE_WSL*a.no_pairs) ,2) as artpricewspedp,round(sum(b.ART_PRICE_MRP*a.no_pairs) ,2) as artpricemrpedp ,round(sum(b.ART_PRICE_FACTORY*a.no_pairs) ,2) as artpricefactoryedp,round(sum(b.art_stnd_cost*a.no_pairs)  ,2)  as artstdCostedp from  tt_bill_punch_dtls_one a,tm_articles_master_dtls b where b.art_no=a.art_code and a.rcpt_inv_no like ?1 and a.party_code like ?2 and a.ord_no like ?3 and a.status='CLOSED' ")
	public BillPunchResponseInterface findWithArticleDetailsStrazaReport(String invno,String partyCode,String ordno);
	*/
	
	@Query(nativeQuery = true, value = "select  round(sum(b.ART_PRICE_WSL*a.no_pairs) ,2)*100 as artpricewspedp,round(sum(b.ART_PRICE_MRP*a.no_pairs) ,2)*100 as artpricemrpedp ,round(sum(b.ART_PRICE_FACTORY*a.no_pairs) ,2)*100 as artpricefactoryedp,round(sum(b.art_stnd_cost*a.no_pairs)  ,2)*100  as artstdCostedp from  tt_bill_punch_dtls_one a,tm_old_articles_master_dtls b where b.art_no=a.art_code and a.rcpt_inv_no like ?1 and a.party_code like ?2 and a.GRNO like ?3 and a.status='CLOSED'")
	public BillPunchResponseInterface findWithArticleDetailsEdpReport(String invno,String partyCode,String grno);
	
	@Query(nativeQuery = true, value = "select  round(sum(b.ART_PRICE_WSL*a.no_pairs) ,2) as artpricewspedp,round(sum(b.ART_PRICE_MRP*a.no_pairs) ,2) as artpricemrpedp ,round(sum(b.ART_PRICE_FACTORY*a.no_pairs) ,2) as artpricefactoryedp,round(sum(b.art_stnd_cost*a.no_pairs)  ,2)  as artstdCostedp from  tt_bill_punch_dtls_one a,tm_old_articles_master_dtls b where b.art_no=a.art_code and a.rcpt_inv_no like ?1 and a.party_code like ?2 and a.ord_no like ?3 and a.status='CLOSED' ")
	public BillPunchResponseInterface findWithArticleDetailsStrazaReport(String invno,String partyCode,String ordno);
	
	
	
	

}
