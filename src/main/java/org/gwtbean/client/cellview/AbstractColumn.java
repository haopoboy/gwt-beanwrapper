package org.gwtbean.client.cellview;

import org.gwtbean.client.BeanObject;
import org.gwtbean.client.MethodNotSupportedException;
import org.gwtbean.shared.Assertions;

import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * Support UiBinder that implements {@link IsWidget}.
 * For child class to extends it and specific cell type.
 * 
 * @param <T> type of {@link BeanObject}
 * @author HaoPo Liao
 * 
 */
public abstract class AbstractColumn<T extends BeanObject> implements IsWidget, IsColumn<T> {

	private final String propertyPath;
	private String header;
	private String footer;
	private String width;
	
	/**
	 * 
	 * @param cellType
	 * @param propertyPath
	 */
	public AbstractColumn(String propertyPath) {
		super();
		Assertions.notNull(propertyPath, "propertyPath cannot be null");
		this.propertyPath = propertyPath;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(AbstractCellTable<T> cellTable) {
		new ColumnBuilder<T>(cellTable)
		.buildColumn( createColumn(), propertyPath )
		.setHeaderString(header)
		.setFooterString(footer)
		.setWidth(width)
		.finish();
	}
	
	@Override
	public Widget asWidget() {
		throw new MethodNotSupportedException();
	}

	public String getPropertyPath() {
		return propertyPath;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Override it if wants to custom Column.
	 * 
	 * @return
	 */
	protected abstract Column<T, ?> createColumn();
}