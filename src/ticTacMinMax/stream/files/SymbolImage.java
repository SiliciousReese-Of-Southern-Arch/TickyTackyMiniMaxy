package ticTacMinMax.stream.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class SymbolImage {
	private BufferedImage xHalf;
	private BufferedImage yHalf;

	/**
	 * An image for symbols on the tic tac toe board.
	 * 
	 * @param imageLoc
	 *            The location of the image
	 * @throws NullPointerException
	 *             If imageLoc is null.
	 */
	public SymbolImage(Path pathToImage) {
		// TODO Prevent possible exceptions
		BufferedImage fullImage = null;
		// Throws null pointer if imageLoc is null.
		File imageFile = pathToImage.toFile();
		try {
			fullImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO Deal with the image not being read.

		int width = fullImage.getWidth() / 2;
		int height = fullImage.getHeight();

		// The x is the right half of the image
		xHalf = fullImage.getSubimage(width, 0, width, height);

		// The y is the left half of the image
		yHalf = fullImage.getSubimage(0, 0, width, height);
	}

	public BufferedImage getXImage() {
		return xHalf;
	}

	public BufferedImage getOImage() {
		return yHalf;
	}

}
