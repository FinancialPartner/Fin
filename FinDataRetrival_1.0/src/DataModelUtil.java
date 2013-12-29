import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.util.Map;
import java.util.Set;


public class DataModelUtil {
	static String DB_URL = "jdbc:mysql://127.0.0.1:3306/financial";
	static String USER_NAME = "financial";
	static String PASSWORD = "welcome1";
	static Connection CONNECTION;	
	public static int insertFinancialStatement(FinancialStatement financialStatement) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		int num = 0;
		try {
			String sql = insertSql(financialStatement);
			System.out.println(sql);
			CONNECTION = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			PreparedStatement pStmt = CONNECTION.prepareStatement(sql);
			CONNECTION.setAutoCommit(false);
			pStmt.setTimestamp(1, financialStatement.getReleaseTime());
			num = pStmt.executeUpdate();
			CONNECTION.commit();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try{
				CONNECTION.rollback();
			}catch(SQLException ex){
				e.printStackTrace();
			}
		}finally{
			CONNECTION.close();
		}
		return num;
	}
	
	private static String insertSql(FinancialStatement financialStatement){
		StringBuffer sbColumn = new StringBuffer();
		StringBuffer sbValue  = new StringBuffer();
		sbColumn.append("INSERT INTO ");
		if ("BALANCE".equalsIgnoreCase(financialStatement.getStatementType())){
			sbColumn.append("balance_statement ( ");
		}else if ("INCOME".equalsIgnoreCase(financialStatement.getStatementType())){
			sbColumn.append("income_statement ( ");
		}else if ("CASH".equalsIgnoreCase(financialStatement.getStatementType())){
			sbColumn.append("cash_statement ( ");
		}
		
		sbColumn.append("stock_id, release_time, institution_type");
		sbValue.append("values(" + financialStatement.getStockId() + ", ?, 'Company' ");
		Set<Map.Entry<FinLookupSeed, Double>> entrySet = financialStatement.getDataMap().entrySet();
		for(Map.Entry<FinLookupSeed, Double> entry : entrySet){
			sbColumn.append(", " + entry.getKey().getColumnName());
			sbValue.append(", " + entry.getValue().toString());
		}
		sbColumn.append(")");
		sbValue.append(")");
		sbColumn.append(sbValue);
		return sbColumn.toString();
	}
	
}
