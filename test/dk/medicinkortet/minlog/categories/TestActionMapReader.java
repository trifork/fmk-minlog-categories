package dk.medicinkortet.minlog.categories;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import dk.medicinkortet.minlog.categories.ActionMapReader;

public class TestActionMapReader {
		
	@Test
	public void testCanReadFile() throws IOException {
		ActionMap mappings = ActionMapReader.getActionMap("MinLogActionMap.csv");
		assertTrue(mappings.getActionMap().size() > 0);
		assertEquals(mappings.getActionMap().get(new Action("Hent lægemiddelordination", false)), "Opslag på medicinoplysninger");
		assertEquals(mappings.getActionMap().get(new Action("Hent lægemiddelordination", true)), "Administratoropslag");
	}
}
