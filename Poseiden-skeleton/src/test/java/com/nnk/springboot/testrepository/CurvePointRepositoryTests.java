package com.nnk.springboot.testrepository;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointRepositoryTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		Assert.assertNotNull(curvePoint.getId());
		Assert.assertTrue(curvePoint.getCurveId() == 10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		Assert.assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		Assert.assertFalse(curvePointList.isPresent());
	}

}
