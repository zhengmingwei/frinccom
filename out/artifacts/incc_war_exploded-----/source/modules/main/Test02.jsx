import React from 'react';
import ReactDOM from 'react-dom';

import SubPage from 'modules/common/SubPage';
/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class Welcome extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <SubPage breadcrumb="/首页/测试/测试02">
                Test02
            </SubPage>
        )
    }
};