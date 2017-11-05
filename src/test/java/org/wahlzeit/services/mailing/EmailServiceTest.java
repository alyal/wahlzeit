/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.services.mailing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailServiceTest {

	EmailService emailService = null;
	EmailAddress validAddress = null;
	EmailAddress invalidAdress = null;

	@Before
	public void setup() throws Exception {
		emailService = EmailServiceManager.getDefaultService();
		validAddress = EmailAddress.getFromString("test@test.de");
	}

	@Test
	public void testSendInvalidEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendValidEmail() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	// Some more Test because first two test were already implemented

	@Test
	public void testSendAnotherInvalidEmail() {
		this.invalidAdress = EmailAddress.getFromString("test@invalid.de");
		try {
			assertFalse(emailService.sendEmailIgnoreException(invalidAdress, null, "Test", "You shall not pass!"));
			assertFalse(emailService.sendEmailIgnoreException(null, invalidAdress, "Test", "Houston we have a problem!"));
			assertFalse(emailService.sendEmailIgnoreException(invalidAdress, invalidAdress, null, "It's a trap!"));
			assertFalse(emailService.sendEmailIgnoreException(invalidAdress, invalidAdress, "Test", null));
			assertFalse(emailService.sendEmailIgnoreException(null, null, "Test", null));
			assertFalse(emailService.sendEmailIgnoreException(null, null, null, "I have a bad feeling about this!"));
		} catch (Exception e) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendAnotherValidEmail() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "I love it when a plan comes together"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

}