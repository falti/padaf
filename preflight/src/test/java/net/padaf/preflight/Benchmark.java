package net.padaf.preflight;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.activation.FileDataSource;

import net.padaf.preflight.ValidationResult.ValidationError;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Benchmark {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		if ( args.length < 3 ) {
			System.err.println("Usage : Benchmark loop resultFile <file1 ... filen|dir>");
			System.exit(255);
		}

		Integer loop = Integer.parseInt(args[0]);
		FileWriter resFile = new FileWriter(new File(args[1]));

		List<File> lfd = new ArrayList<File>();
		for (int i = 2; i < args.length ; ++i) {
			File fi = new File(args[i]);
			if (fi.isDirectory()) {
				Collection<File> cf = FileUtils.listFiles(fi, null, true); // Get All files contained by the dir
				lfd.addAll(cf);
			} else {
				lfd.add(fi);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.Z");

		PdfAValidator validator = new PdfAValidatorFactory().createValidatorInstance(PdfAValidatorFactory.PDF_A_1_b);
		long startGTime = System.currentTimeMillis();
		
		int size = lfd.size();
		for (int i = 0 ; i < loop ; i++) {
			File file = lfd.get(i%size);
			long startLTime = System.currentTimeMillis();
			ValidationResult result = validator.validate(new FileDataSource(file));
			if (!result.isValid()) {
				resFile.write(file.getAbsolutePath() + " isn't PDF/A\n");
				for (ValidationError error : result.getErrorsList()) {
					resFile.write(error.getErrorCode() + " : " + error.getDetails() +"\n");
				}
			}
			result.closePdf();
			long endLTime = System.currentTimeMillis();
			resFile.write(file.getName() + " (ms) : " + (endLTime - startLTime) + "\n");
			resFile.flush();
		}

		long endGTime = System.currentTimeMillis();

		resFile.write("Start : " + sdf.format(new Date(startGTime)) +"\n");
		resFile.write("End : " + sdf.format(new Date(endGTime)) +"\n");
		resFile.write("Duration (ms) : " + (endGTime - startGTime) +"\n");
		resFile.write("Average (ms) : " + (int)((endGTime - startGTime)/loop) +"\n");
		
		System.out.println("Start : " + sdf.format(new Date(startGTime)));
		System.out.println("End : " + sdf.format(new Date(endGTime)));
		System.out.println("Duration (ms) : " + (endGTime - startGTime));
		System.out.println("Average (ms) : " + (int)((endGTime - startGTime)/loop));
		resFile.flush();
		IOUtils.closeQuietly(resFile);
	}
}