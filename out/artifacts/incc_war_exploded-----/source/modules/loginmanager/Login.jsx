import React from 'react';
import ReactDOM from 'react-dom';

import { Breadcrumb } from 'antd';

export default class Login extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div>
				<div className="ant-layout-container">
					<div className="ant-layout-content">
						<div style={{ height: 590 }}>
							欢迎登录管理平台！
						</div>
					</div>
				</div>
			</div>
		)
	}
};