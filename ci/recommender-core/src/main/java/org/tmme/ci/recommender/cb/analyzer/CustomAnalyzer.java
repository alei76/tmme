package org.tmme.ci.recommender.cb.analyzer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LengthFilter;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

public class CustomAnalyzer extends Analyzer {

	@Override
	public TokenStream tokenStream(final String fieldName, final Reader reader) {
		// TODO include HTMLStrip
		TokenStream result = new StandardTokenizer(Version.LUCENE_36, reader);
		result = new LowerCaseFilter(Version.LUCENE_36, result);
		result = new LengthFilter(false, result, 3, 50);
		result = new StopFilter(Version.LUCENE_36, result,
				StandardAnalyzer.STOP_WORDS_SET);
		result = new PorterStemFilter(result);
		return result;
	}

}
