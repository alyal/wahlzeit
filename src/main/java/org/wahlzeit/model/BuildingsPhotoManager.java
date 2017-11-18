package org.wahlzeit.model;

import java.util.logging.Logger;

/**
 * BuildingsPhotoManager is a subclass of {@PhotoManager}.
 */
public class BuildingsPhotoManager extends PhotoManager {

	/**
	 *
	 */
	protected static final BuildingsPhotoManager instance = new BuildingsPhotoManager();

	public BuildingsPhotoManager() {
		photoTagCollector = BuildingsPhotoFactory.getInstance().createPhotoTagCollector();
	}

	/**
	 *
	 */
	@Override
	public Photo getPhotoFromId(PhotoId id) {
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
