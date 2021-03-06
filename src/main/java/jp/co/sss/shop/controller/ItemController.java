package jp.co.sss.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.repository.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	ItemRepository repository;

	@Autowired
	HttpSession session;

	@RequestMapping("/items/findAll")
	public String showItemList(Model model) {
		model.addAttribute("items", repository.findAll());
		return "items/item_list";
	}

	@RequestMapping("/items/findAllByOrderByPriceDesc")
	public String showItemListByOrderByPriceDesc(Model model) {
		model.addAttribute("items", repository. findAllByOrderByPriceDesc());
		return "items/item_list";
	}


	@RequestMapping("/items/getOne/{id}")
	public String showItem(@PathVariable int id, Model model) {
//		model.addAttribute("item", repository.getOne(id));
		model.addAttribute("item", repository.findById(id));
		return "items/item";
	}

	@RequestMapping("/items/findByPrice/{price}")
	public String showItemListByPrice(@PathVariable int price, Model model) {
		model.addAttribute("items", repository.findByPrice(price));
		return "items/item_list";
	}

	@RequestMapping("/items/findByNameAndPrice/{name}/{price}")
	public String showItemListByNameAndPrice(@PathVariable String name,@PathVariable int price,
	Model model) {
		model.addAttribute("items", repository.findByNameAndPrice(name, price));
		return "items/item_list";
	}

	@RequestMapping("/items/findByNameLike/{name}")
	public String showItemListByNameLike(@PathVariable String name, Model model) {
		model.addAttribute("items", repository.findByNameLike("%" + name + "%"));
		return "items/item_list";
	}

	@RequestMapping("/items/findAllAndSetDropdown")
	public String ItemListSetDropdown(Model model) {
		session.setAttribute("items", repository.findAll());
		return "items/item_list_dropdown";
	}

	@RequestMapping("/items/create/input")
	public String createInput() {
		return "items/create_input";
	}

	@RequestMapping(path = "/items/create/complete", method = RequestMethod.POST)
	public String createComplete(ItemForm form) {
		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		repository.save(item);
		return "redirect:/items/getOne/" + item.getId();
	}

	@RequestMapping("/items/update/input/{id}")
	public String updateInput(@PathVariable int id, Model model) {
		model.addAttribute("item", repository.getOne(id));
		return "items/update_input";
	}

	@RequestMapping(path = "/items/update/complete/{id}", method = RequestMethod.POST)
	public String updateComplete (@PathVariable int id, ItemForm form) {
		Item item = repository.getOne(id);
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		repository.save(item);
		return "redirect:/items/getOne/" + item.getId();
	}

	@RequestMapping("/items/delete/input")
	 public String deleteInput() {
		return "items/delete_input";
	 }

	 @RequestMapping(path = "/items/delete/complete")
	 public String deleteComplete(ItemForm form) {
		 repository.deleteById(form.getId());
		 return "redirect:/items/findAll";
	 }

	 @RequestMapping("/items/findAllJs")
	 public String showItemListJs(Model model) {
		 model.addAttribute("items", repository.findAll());
		 return "items/item_list_js";
	 }


}