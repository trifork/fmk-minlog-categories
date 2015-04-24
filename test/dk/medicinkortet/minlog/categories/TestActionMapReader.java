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
		Map<ActionMapReader.Action, String> mappings = ActionMapReader.getActionMap("MinLogActionMap.csv");
		assertTrue(mappings.size() > 0);
		assertEquals(mappings.get(new ActionMapReader.Action("Hent lægemiddelordination", false)), "Opslag på medicinoplysninger");
		assertEquals(mappings.get(new ActionMapReader.Action("Hent lægemiddelordination", true)), "Administratoropslag");
	}
}
