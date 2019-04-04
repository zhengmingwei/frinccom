import React from 'react';
import {Icon, Table, Modal, Row, Col} from 'antd';
import {refreshDistributorList, changeDistributorStatus, delDistributor} from 'actions/DistributorActions';
import {showModalDialog} from 'actions/CommonAction';
import DelConfirm from '../common/DelConfirm';

const confirm = Modal.confirm;
/**
 * Distributor: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class DistributorList extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
        this.props.onView(id);
    }

    edit(id){
        this.props.onEdit(id);
    }

    delete(id, distributorCriteria, pageNum){
        this.props.dispatch(delDistributor(id, distributorCriteria));
        // this.props.onDelete(id);
    }

    render() {

        const columns = [
            {title: '编号', dataIndex: '', key: 'num', render: (text, record, index) => (

                <span>
                    {index + 1}
                </span>

            )},
            {title: '企业名称', dataIndex: 'name', key: 'name'},
            {title: '法人', dataIndex: 'legalPerson', key: 'legalPerson'},
            {title: '组织机构代码', dataIndex: 'orgCode', key: 'orgCode'},
            {title: '联系人', dataIndex: 'contact', key: 'contact'},
            {title: '电话', dataIndex: 'phone', key: 'phone'},
            {title: '传真', dataIndex: 'fax', key: 'fax'},
            {
                title: '操作', dataIndex: '', key: 'x', render: (text, record, index) => (

                <span>
                    <a onClick={()=>this.view(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 查看</a>
                    <a onClick={()=>this.edit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 修改</a>
                    <DelConfirm onOk = {()=>this.delete(record.id, distributorCriteria, distributorList.pageNum)}/>
                </span>

            ),
            },
        ];
        const {distributorList} = this.props;

        const {distributorCriteria} = this.props;

        const pagination = {
            total: distributorList.total,
            pageSize: distributorList.pageSize,
            current: distributorList.pageNum,
            onChange: (page) => {
                this.props.dispatch(refreshDistributorList(distributorCriteria, {"pageNum": page}));
            },
        };

        return (
            <div>
                <Table
                    columns={columns}
                    bordered
                    dataSource={distributorList.list}
                    pagination={pagination}
                    className="table"
                />
            </div>
        )
    }
};
