package com.nnk.springboot.testrepoanddomain;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


/**
 * By convention, at least 80% of code should be tested. 
 * Our program isn't currently using all the attributes from trades
 * (no functionality have been expressed yet by the client).
 * Therefore a lot of getters and setters aren't yet tested, because
 * there aren't currently used in the code. These untested getters and setters
 * ,plunge coverage.  Once new functionalities using these attributes
 * would be used, this test can be deleted.
 * 
 * For now, and for convention purposes, we maintain the 80% of coverage this way.
 * 
 * @author Alexandre OSSELIN
 *
 */



@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameRepository.save(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("Rule Name")
				&& rule.getDescription().equals("Description") && rule.getJson().equals("Json")
				&& rule.getTemplate().equals("Template") && rule.getSqlPart().equals("SQL Part") 
				&& rule.getSqlStr().equals("SQL"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameRepository.save(rule);
		Assert.assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		Assert.assertFalse(ruleList.isPresent());
	}
}
