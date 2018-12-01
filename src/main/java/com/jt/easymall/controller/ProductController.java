package com.jt.easymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.easymall.pojo.Product;
import com.jt.easymall.service.ProductService;
import com.jt.easymall.vo.EasyUIResult;
import com.jt.easymall.vo.Page;
import com.jt.easymall.vo.SysResult;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	//处理商品分页查询,默认都是5条
		/*
		 * 请求url:/product/page?currentPage=1&rows=5
			请求方式:get
			请求参数:Integer currentPage,Integer rows
			备注: 当前的页面数字,和显式商品的条数;
			可以底层获取数据时使用参数
			select * from t_product limit (currentPage-1)*rows,rows
			返回数据: 页面渲染的字符串 
			WEB-INF/views/product_list.jsp
			return "product_list";
			备注:观察页面后,需要一个page对象(int currentPage,int totalPage,List productList) 提供给页面使用,
				通过页面的调用EL表达式,完成首页的展示,中间页的展示,和尾页的展示
		 */
	@RequestMapping("/product/page")
	public String queryProducts(
			Integer currentPage,Integer rows,Model model){
		//����ҵ������,����ҵ���߼�,���ó־ò��ȡ����
		List<Product> pList=productService.
			queryProducts(currentPage,rows);
		//TODO ����Я����ҳ��
		//��Ҫ��װһ��Page����,model("page",page)
		Integer totalPage=productService.queryTotalPage(rows);
		Page page=new Page();
		page.setProducts(pList);
		page.setCurrentPage(currentPage);
		//����service,���־ò�,��,��ҵ����װtotalPage
		page.setTotalPage(totalPage);
		model.addAttribute("page", page);
		return "product_list";
	}
	
	//����restFul��ʽ��·������
	/*@RequestMapping("/{a1}/{a2}/{a3}")
	public String restFulTest(@PathVariable String a1,
			@PathVariable String a2,
			@PathVariable String a3){
		//��ӡһ��a1,a2,a3
		System.out.println(a1+":"+a2+":"+a3);
		return "";
	}*/
	
	/*
	 * 	����url:/product/findProductById/{productId}
		����ʽ:get
		�������:String productId 
		��ע:������װ��·��url�д��ݸ������,
		��Ҫ����Springmvc��restFul��ʽ����·������
		 @PathVariable String productId
	 */
	
	
	/*
	 * 
	 * restFul���β���;
	   url:localhost/haha/name/wanglaoshi
 	   @RequestMapping("/{a1}/{a2}/{a3}")
	   public String (@PahtVariable String a1,@PathVariable String a2,@PathVariable String a3)

	        ҳ�������ȡ�ӿ��ļ�������
		����url:/product/findProductById/{productId}
		 ����ʽ:get
		 �������:String productId 
		 ��ע:������װ��·��url�д��ݸ������,��Ҫ����Springmvc��restFul��ʽ����·������ @PathVariable String productId
		 ������Ʒid,��ѯ��Ʒ����Ϣ,��װһ��product����
		 sql���:select * from t_product where product_id=#{id};
		 ��������: ��Ҫҳ�������"product_info"
		 ��ע:��Ҫģ�����modelЯ������id��ѯ����product����
		 model.addAttribute("product",product);
	 */
	
	@RequestMapping("/product/findProductById/{id}")
	public String findProductById(@PathVariable String id,
			Model model){
		
		
		Product product=productService.findProductById(id);
		model.addAttribute("product",product);
		return "product_info";
		//{"category":"asdklfj","name":"dasklfh","price"}
	}
	
	
	
	
	//��̨�����߼�
	@RequestMapping("/product/save")
	@ResponseBody
	public SysResult saveProduct(Product product){
		SysResult result=new SysResult();
		try{
		int success=productService.saveProduct(product);
		if(success==1){
			//�ɹ�����һ��status=200��sysresult����
			result.setStatus(200);
			result.setMsg("�������Ʒ������");
			return result;
		}else{//ʧ����
			result.setStatus(201);
			result.setMsg(new String("����������ʲô����".getBytes(),"iso8859-1"));
			return result;}
		}catch(Exception e){
			result.setStatus(201);
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	//��̨����Ʒ��ҳ��ѯ
	@RequestMapping("/product/query")
	@ResponseBody
	public EasyUIResult queryManageProducts(
			Integer page,Integer rows){
	List<Product> pList=productService.queryProducts
			(page, rows);
	int total=productService.queryTotal();
	//��װ����list����,��������total��EasyUIResult��,��ajaxʹ��
	EasyUIResult result=new EasyUIResult();
	result.setTotal(total);
	result.setRows(pList);
	
	return result;
	}
	
	
	//��Ʒ�ĸ��²���
	/*����ʽ:post
	����url:/product/update
	�������:Product prduct
	��ǰ���е����б�ǩ���������л���jquery������ȡ
	productCategory=v1&productName=v2&productPrice=v3&productImgurl=v4&productNum=v5&productDescription=v6
	productId=wedrk231bnsdfk213kbr
	
	��ע:sql�����ν��и��²���
	update t_product set product_category=#{productCategory}...
	where product_id=#{productId}
	��������: SysResult�Ķ���json,200�ɹ�,����ʧ��
	*/
	
	@RequestMapping("/product/update")
	@ResponseBody
	public SysResult updateProduct(Product product){
		SysResult result=new SysResult();
		//����service���·���
		int success=productService.updateProduct(product);
		if(success==1){//�޸ĳɹ�
			result.setStatus(200);
			result.setMsg("ok");
			return result;
		}else{//ʧ����,ֻҪ����200���Ǳ�ʾʧ��
			result.setStatus(201);
			return result;
		}
	}
	
	
	/*
	 * 	����url:/product/delete
	����ʽ:post
	�������:String[] ids ��ǰ����ѡ��Ķ����Ʒ��id
	��������:
	SysResult��json�ַ���
	status 200�ɹ� ����ʧ��
	
	��ʾ:forѭ��,����ɾ��
	ÿ��ɾ����sql
	delete from t_product where id=#{id}
	 */
	@RequestMapping("/product/delete")
	@ResponseBody
	public SysResult deleteProduct(String[] ids){
		SysResult result=new SysResult();
		int success=productService.deleteProduct(ids);
		
		
		if(success==1){//�޸ĳɹ�
			result.setStatus(200);
			result.setMsg("ɾ�����");
			return result;
		}else{//ʧ����,ֻҪ����200���Ǳ�ʾʧ��
			result.setStatus(201);
			result.setMsg("ɾ��ʧ��");
			return result;
		}
	}
	
	
}




