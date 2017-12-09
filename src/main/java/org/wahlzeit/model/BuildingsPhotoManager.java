package org.wahlzeit.model;

import java.util.logging.Logger;

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
	public Photo getPhotoFromId(PhotoId id) {
		assertInstanceOfPhotoId(id);
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

	/**
	 * @throws IllegalArgumentException
	 * @mthodtype: assertion
	 */
	private void assertInstanceOfPhotoId(Object o) throws IllegalArgumentException {
		if (!(o instanceof PhotoId)) {
			log.warning("Passed argument is not an instance of PhotoId, but was: " + o.getClass().getName());
			throw new IllegalArgumentException("Passed argument is not an instance of PhotoId, but was!");
		}

	}

}
