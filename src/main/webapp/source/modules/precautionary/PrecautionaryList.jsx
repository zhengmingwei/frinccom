import React from 'react';
import {Icon, Table, Modal, Row, Col} from 'antd';
import {refreshPrecautionaryList} from 'actions/PrecautionaryActions';
import {showModalDialog} from 'actions/CommonAction';
import DelConfirm from '../common/DelConfirm';
const confirm = Modal.confirm;
/**
 * Precautionary: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class PrecautionaryList extends React.Component {
    constructor(props) {
        super(props);
    }
    
render() {
    const {precautionaryList} = this.props;
    const param = this.props.params.id;
    const {currentUser} = this.props;
    const {precautionaryCriteria} = this.props;

        const columns = [
            {title: '企业名称', dataIndex: 'companyName', key: 'companyName'},
            {title: '产品名称', dataIndex: 'commodityName', key: 'commodityName'},
            {title: '组织机构代码', dataIndex: 'orgCode', key: 'orgCode'},
            {title: '标识授权开始时间', dataIndex: 'beginDate', key: 'beginDate'},
            {title: '标识授权结束时间', dataIndex: 'expireDate', key: 'expireDate'},
        ];

        const pagination = {
            total: precautionaryList.total,
            pageSize: precautionaryList.pageSize,
            current: precautionaryList.pageNum,
            onChange: (page) => {
                this.props.dispatch(refreshPrecautionaryList(precautionaryCriteria, {"pageNum": page}));
            },
        };

        return (
            <div>
                <Table
                    columns={columns}
                    bordered
                    dataSource={precautionaryList.list}
                    pagination={pagination}
                    className="table"
                />
            </div>
        )
    }
};
