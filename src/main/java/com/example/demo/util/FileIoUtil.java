package com.example.demo.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FileIoUtil {
	/**
	 * SQLファイル読み込み
	 * @param sqlFileName SQLファイル名(拡張子不要)
	 * @param dml 実行DMLタイプ
	 * @return　実行SQL
	 * @throws IOException
	 */
	public String readSqlFile(String sqlFileName, DmlType dml) throws IOException {

		StringBuffer sqlFilePath = new StringBuffer("./target/classes/sql/");
		StringBuffer query = new StringBuffer();
		switch (dml) {
		case SELECT:
			sqlFilePath.append("select/");
			break;
		case INSERT:
			sqlFilePath.append("insert/");
			break;
		case UPDATE:
			sqlFilePath.append("update/");
			break;
		case DELETE:
			sqlFilePath.append("delete/");
			break;
		}

		sqlFilePath.append(sqlFileName);
		sqlFilePath.append(".sql");

		List<String> lines = Files.readAllLines(Paths.get(sqlFilePath.toString()), StandardCharsets.UTF_8);
		for (String line : lines) {
			query.append(line);
			query.append(" ");
		}
		
		return query.toString().replaceAll("\t", " ");
	}
}
