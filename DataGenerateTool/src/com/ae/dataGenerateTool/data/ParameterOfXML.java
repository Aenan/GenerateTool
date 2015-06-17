package com.ae.dataGenerateTool.data;

public class ParameterOfXML {
	private String _name;
	private String _type;
	private String _scope;
	private String _default;
	private String _notnull;

	public ParameterOfXML() {
		this._name = null;
		this._type = null;
		this._scope = null;
		this._default = null;
		this._notnull = null;
	}

	public ParameterOfXML(String _name, String _type, String _scope,
			 String _notnull,String _default) {
		super();
		this._name = _name;
		this._type = _type;
		this._scope = _scope;
		this._default = _default;
		this._notnull = _notnull;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_scope() {
		return _scope;
	}

	public void set_scope(String _scope) {
		this._scope = _scope;
	}

	public String get_default() {
		return _default;
	}

	public void set_default(String _default) {
		this._default = _default;
	}

	public String get_notnull() {
		return _notnull;
	}
	

	public void set_notnull(String _notnull) {
		this._notnull = _notnull;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return _name.toString()+_type.toString()+_scope.toString()+_default.toString()+_notnull.toString();
	}
}
