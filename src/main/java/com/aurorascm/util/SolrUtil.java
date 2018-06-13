package com.aurorascm.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Repository;

import com.aurorascm.entity.home.GoodsSolr;


/** solr 搜索引擎
 * @author BYG 2017-12-12
 * @version 1.0
 */
@Repository("solrUtil")
public class SolrUtil {

	private final String solrUrl = "http://120.27.217.255:8983/solr/goods-core";
	//创建solrClient同时指定超时时间，不指定走默认配置
	private HttpSolrClient getsolrClient(){
		HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
		return solrClient;
	}
	
	/**solr搜索随机四个商品
	 * @param pd
	 * @throws Exception
	 * @return Map
	 */
	public Map<String, Object> queryRandom4(PageData pd) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String tradeType = pd.getString("tradeType");
		HttpSolrClient solrClient = this.getsolrClient();
		SolrQuery query = new SolrQuery();				//封装查询参数
		//查询参数
		query.setQuery("*:*");
        //贸易方式-过滤查询
		String fq = "";
		if (Tools.notEmpty(tradeType)) {
			fq += "trade_type:" + tradeType;
			query.addFilterQuery(fq);
		}
		//分页
		int start = new Random().nextInt(10);           //为了随机获取
		query.setStart(start);                          //起始页
		query.setRows(4);								//设置每页显示多少条
		//添加需要回显得字段(若不设置，默认全部回显)
        query.addField("id");					
        query.addField("goods_id");
        query.addField("goods_name");
        query.addField("trade_type");
        query.addField("postage_style");
        query.addField("deposit");
        query.addField("category2_id");
        query.addField("category2_name");
        query.addField("fake_stock");
        query.addField("goods_price2");
        query.addField("currency_unit");
        query.addField("market_price");
        query.addField("main_map");
        //执行查询、结果处理。
        QueryResponse response = solrClient.query(query);	//执行查询返回QueryResponse
        List<GoodsSolr> goodsSolr = new ArrayList<GoodsSolr>();
        if (!response.getBeans(GoodsSolr.class).isEmpty()) {
        	goodsSolr = (List<GoodsSolr>) response.getBeans(GoodsSolr.class);
        }
        map.put("goodsSolr", goodsSolr);
		return map;
	}
	
	/**solr商品详情页根据商品二级累目、品牌搜索随机6个商品
	 * @param pd
	 * @throws Exception
	 * @return Map
	 */
	public List<GoodsSolr> queryRandom6(String goodsID,String category2Name,String brandCname,String brandEname) throws Exception{
		HttpSolrClient solrClient = this.getsolrClient();
		SolrQuery query = new SolrQuery();				//封装查询参数
		//查询参数
		query.setQuery("category2_name:" + category2Name + " OR " + "brand_cname:" + brandCname + " OR " + "brand_ename:" + brandEname);
        //商品ID过滤
		String fq = "-goods_id:" + goodsID;
		query.addFilterQuery(fq);
		//分页
//		int start = new Random().nextInt(5);           //为了随机获取
		query.setStart(0);                          //起始页
		query.setRows(30);							//设置每页显示多少条
		//添加需要回显得字段(若不设置，默认全部回显)
        query.addField("id");					
        query.addField("goods_id");
        query.addField("goods_name");
        query.addField("trade_type");
        query.addField("postage_style");
        query.addField("deposit");
        query.addField("goods_price2");
        query.addField("currency_unit");
        query.addField("main_map");
        //执行查询、结果处理。
        QueryResponse response = solrClient.query(query);	//执行查询返回QueryResponse
        List<GoodsSolr> goodsSolr1 = new ArrayList<GoodsSolr>();
        List<GoodsSolr> goodsSolr = new ArrayList<GoodsSolr>();
        if (!response.getBeans(GoodsSolr.class).isEmpty()) {
        	goodsSolr1 = (List<GoodsSolr>) response.getBeans(GoodsSolr.class);
        }
        if(goodsSolr1.size() > 6){
        	Collections.shuffle(goodsSolr1);
        	for (int i = 0; i < 6; i++) {
        		goodsSolr.add(goodsSolr1.get(i));
			}
        }else {
        	goodsSolr = goodsSolr1;
		}
		return goodsSolr;
	}
	
	/**solr根据关键词搜索
	 * @param pd
	 * @throws Exception
	 * @return Map
	 */
	public Map<String, Object> queryByKeyword(PageData pd) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String keyword = pd.getString("keyword");
		String tradeType = pd.getString("tradeType");
		String orderBY = pd.getString("orderBY");
		String orderAD = pd.getString("orderAD");
		Integer pageNum =  Integer.valueOf(pd.getString("pageNum"));
		Integer pageSize = Integer.valueOf(pd.getString("pageSize"));
		HttpSolrClient solrClient = this.getsolrClient();
		SolrQuery query = new SolrQuery();				//封装查询参数
		String q = "*:*";
		//查询参数(propertys为商品名称、关键字等的组合)
		if (Tools.notEmpty(keyword)) {
			q = "goods_id:" + keyword + " OR " + "goods_code:" + keyword + " OR " + "brand_id:" + keyword + " OR " + "trade_type:" + keyword
			+ " OR " + "postage_style:" + keyword + " OR " + "deposit:" + keyword + " OR " + "category2_id:" + keyword + " OR " + "propertys:" + keyword;
			
		}
		query.setQuery(q);
        //贸易方式-过滤查询(相当于sql where)
		String fq = "";
		if (Tools.notEmpty(tradeType)) {
			fq += "trade_type:" + tradeType;
			query.addFilterQuery(fq);
		}
		//销量、新货、价格排序
		if (!orderBY.equals("4")) {
			String sortField = "";
			if (orderBY.equals("1")) {
				sortField = "goods_sales";
			}else if(orderBY.equals("2")){
				sortField = "up_time";
			}else if(orderBY.equals("3")){
				sortField = "goods_price2";
			}
			if (orderAD.equals("ASC")) {
//				query.addSort(sortField, ORDER.asc);
				query.setSort(sortField, ORDER.asc);
			} else {
//				query.addSort(sortField, ORDER.desc);
				query.setSort(sortField, ORDER.desc);
			}
		}else {
			SortClause sortClause2 = new SortClause("goods_price2", ORDER.asc);
			SortClause sortClause1 = new SortClause("goods_sales", ORDER.desc);
			SortClause sortClause3 = new SortClause("up_time", ORDER.desc);
			List<SortClause> sortClause = new ArrayList<>();
			sortClause.add(sortClause1);
			sortClause.add(sortClause2);
			sortClause.add(sortClause3);
			query.setSorts(sortClause);
		}
		//分页
		Integer start = (pageNum - 1) * pageSize;
		query.setStart(start);                          	//起始数
		query.setRows(pageSize);							//设置每页显示多少条
		//添加需要回显得字段(若不设置，默认全部回显)
        query.addField("id");					
        query.addField("goods_id");
        query.addField("goods_name");
        query.addField("brand_id");
        query.addField("brand_cname");
        query.addField("brand_ename");
        query.addField("trade_type");
        query.addField("postage_style");
        query.addField("deposit");
        query.addField("category2_id");
        query.addField("category2_name");
        query.addField("fake_stock");
        query.addField("goods_price2");
        query.addField("currency_unit");
        query.addField("market_price");
        query.addField("main_map");
        //执行查询、结果处理。
        QueryResponse response = solrClient.query(query);	//执行查询返回QueryResponse
        long totalRecord = response.getResults().getNumFound();       //总记录数
        List<GoodsSolr> goodsSolr = new ArrayList<GoodsSolr>();
//        goodsSolr = (List<GoodsSolr>) response.getBeans(GoodsSolr.class);
        if (!response.getBeans(GoodsSolr.class).isEmpty()) {
        	goodsSolr = (List<GoodsSolr>) response.getBeans(GoodsSolr.class);
		}
        map.put("goodsSolr", goodsSolr);
        map.put("totalRecord", totalRecord);
		return map;
	}
	
	/**solr搜索--品牌下的商品
	 * @param pd
	 * @throws Exception
	 * @return Map
	 */
	public Map<String, Object> queryByBrand(PageData pd) throws Exception{
		String tradeType = pd.getString("tradeType");
		String orderBY = pd.getString("orderBY");
		String orderAD = pd.getString("orderAD");
		Integer pageNum =  Integer.valueOf(pd.getString("pageNum"));
		String brandID = pd.getString("brandID");
		Integer pageSize = Integer.valueOf(pd.getString("pageSize"));
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSolrClient solrClient = this.getsolrClient();
		SolrQuery query = new SolrQuery();				//封装查询参数
		String q ="brand_id:" + brandID;
		query.setQuery(q);
		//贸易方式-过滤查询
		String fq = "";
		if (Tools.notEmpty(tradeType)) {
			fq += "trade_type:" + tradeType;
			query.addFilterQuery(fq);
		}
		//销量、新货、价格排序
		if (!orderBY.equals("4")) {
			String sortField = "";
			if (orderBY.equals("1")) {
				sortField = "goods_sales";
			}else if(orderBY.equals("2")){
				sortField = "up_time";
			}else if(orderBY.equals("3")){
				sortField = "goods_price2";
			}
			if (orderAD.equals("ASC")) {
				query.addSort(sortField, ORDER.asc);
			} else {
				query.addSort(sortField, ORDER.desc);
			}
		}
		//分页
		Integer start = (pageNum - 1) * pageSize;
		query.setStart(start);                          	//起始数（从零开始）
		query.setRows(pageSize);							//设置每页显示多少条
		//添加需要回显得字段(若不设置，默认全部回显)
        query.addField("id");					
        query.addField("goods_id");
        query.addField("goods_name");
        query.addField("trade_type");
        query.addField("postage_style");
        query.addField("deposit");
        query.addField("category2_id");
        query.addField("category2_name");
        query.addField("fake_stock");
        query.addField("goods_price2");
        query.addField("currency_unit");
        query.addField("market_price");
        query.addField("main_map");
        //执行查询、结果处理。
        QueryResponse response = solrClient.query(query);	//执行查询返回QueryResponse
        long totalRecord = response.getResults().getNumFound();       //总记录数
        List<GoodsSolr> goodsSolr = new ArrayList<GoodsSolr>();
        if (!response.getBeans(GoodsSolr.class).isEmpty()) {
        	goodsSolr = (List<GoodsSolr>) response.getBeans(GoodsSolr.class);
		}
        map.put("goodsSolr", goodsSolr);
        map.put("totalRecord", totalRecord);        
		return map;
	}
	
	/**solr搜索词自动补全(前缀搜索模式)
	 * @param prefix
	 * @throws Exception
	 * @return List<String>
	 */
	public List<String> autoComplete(String prefix) throws Exception{
		HttpSolrClient solrClient = this.getsolrClient();
		SolrQuery query = new SolrQuery();				//封装查询参数
		query.setQuery("auto_spell:" + prefix);
		query.addField("goods_name");
		query.setStart(0);                          	//起始页
		query.setRows(10);
		QueryResponse response = solrClient.query(query);
		List<String> autoComplete = new ArrayList<>();
		List<GoodsSolr> goodsSolr = new ArrayList<GoodsSolr>();
        if (!response.getBeans(GoodsSolr.class).isEmpty()) {
        	goodsSolr = (List<GoodsSolr>) response.getBeans(GoodsSolr.class);
        	for (GoodsSolr g : goodsSolr) {
        		autoComplete.add(g.getGoods_name());
			}
		}
        HashSet h = new HashSet(autoComplete); 
        autoComplete.clear();   
        autoComplete.addAll(h);
		return autoComplete;
	}
//	/**solr搜索词自动补全(Facet模式)
//	 * @param prefix
//	 * @throws Exception
//	 * @return List<String>
//	 */
//	@Test
//	public void autoComplete() throws Exception{
//		String prefix = "喜宝";
//		HttpSolrClient solrClient = this.getsolrClient();
//		SolrQuery query = new SolrQuery();				//封装查询参数
//		query.setQuery("auto_spell:*" + prefix + "*");
//		query.setRows(0);//这样可以只返回facet的结果，而没有查询结果。当然这不是必须的。
//		query.setFacet(true);
//		String[] fields={"auto_spell"};
//		query.addFacetField(fields);
////		query.setFacetPrefix(prefix);
//		query.setFacetLimit(10);// 限制facet返回的数量
//		query.setFacetMinCount(1);// 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
//		
//		 QueryResponse response = solrClient.query(query);
//		 List<PageData> autoComplete = new ArrayList<PageData>();
//		 List<FacetField> facetFields = response.getFacetFields();
//		 if (facetFields.size() >= 1) {
//			 for (FacetField ff : facetFields) {
//				 List<Count> counts = ff.getValues();
//				 if (counts.size() >= 1) {
//					 for (Count count : counts){
//				            System.out.println(count.getName()+":"+ count.getCount());
//				            PageData pd = new PageData();
//				            pd.put("name", count.getName());
//				            pd.put("count", count.getCount());
//				            autoComplete.add(pd);
//				     }
//				 }
//			}
//		 }
//		 System.out.println(autoComplete);  
//	}
//	
//	/**solr搜索词自动补全(suggest模式)
//	 * @param prefix
//	 * @throws Exception
//	 * @return List<String>
//	 */
//	@Test
//	public void getSuggest() throws Exception{
//		String prefix = "喜宝";
//		HttpSolrClient solrClient = this.getsolrClient();
//		SolrQuery query = new SolrQuery();				//封装查询参数
//		query.setRequestHandler("/suggest");
//		query.setQuery("auto_spell:*" + prefix + "*");
//		 QueryResponse response = solrClient.query(query);
//		 SpellCheckResponse checkResponse = response.getSpellCheckResponse();
//		 List<String> autoComplete = new ArrayList<>();
//		 if(checkResponse!=null){  
//	         List<Suggestion> suggestions = checkResponse.getSuggestions();  
//	             
//	         for (Suggestion s : suggestions) {  
//		         List<String> list = s.getAlternatives();  
//		         for (String spellword : list) {  
//			         System.out.println(spellword);  
//			         autoComplete.add(spellword);  
//		         }  
//	         }  
//	     }
//		 System.out.println(autoComplete);
//	}
	

	
}
