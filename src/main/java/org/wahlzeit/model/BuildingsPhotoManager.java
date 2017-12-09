package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.utils.AssertionUtils;

/**
 * BuildingsPhotoManager is a subclass of {@PhotoManager}.
 */
public class BuildingsPhotoManager extends PhotoManager {

	private static final Logger log = Logger.getLogger(BuildingsPhotoManager.class.getName());

	/**
	 *
	 */
	protected static final BuildingsPhotoManager instance = new BuildingsPhotoManager();

	public BuildingsPhotoManager() {
		photoTagCollector = BuildingsPhotoFactory.getInstance().createPhotoTagCollector();
	}

	/**
	 * @throws IllegalArgumentException
	 *
	 */
	@Override
	public Photo getPhotoFromId(PhotoId id) throws IllegalArgumentException {
		AssertionUtils.assertInstanceOfPhotoId(id, this.getClass().getSimpleName());
		if (id == null) {
			return null;
		}

		Photo result = doGetPhotoFromId(id);

		if (result == null) {
			result = BuildingsPhotoFactory.getInstance().loadPhoto(id);
			if (result != null) {
				doAddPhoto(result);
			}
		}

		return result;
	}

}
