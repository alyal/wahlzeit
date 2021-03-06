/*
 * Copyright (c) 2017 by alyal, https://github.com/alyal
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

package org.wahlzeit.model;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.model.persistence.PersistenceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessRightsTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        LocationTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        PersistenceTestSuite.class,
        BuildingTest.class,
        BuildingsPhotoManagerTest.class,
        BuildingPhotoTest.class,
        BuildingsPhotoFactoryTest.class,
        CartesianCoordinateTest.class,
        SphericCoordinateTest.class,
        BuildingsManagerTest.class,
        BuildingsTypeTest.class
})

/**
 * Test suite, that collects/combines all model tests  
 *
 */
public class ModelTestSuite {
}
