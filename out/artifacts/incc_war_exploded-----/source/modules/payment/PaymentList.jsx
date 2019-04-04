import React from 'react';
import {Icon, Table, Modal, Row, Col} from 'antd';
import {refreshPaymentList, changePaymentStatus, delPayment} from 'actions/PaymentActions';
import {showModalDialog} from 'actions/CommonAction';
import DelConfirm from '../common/DelConfirm';

const confirm = Modal.confirm;
/**
 * Payment: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class PaymentList extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
        this.props.onView(id);
    }

    edit(id){
        this.props.onEdit(id);
    }

    delete(id, paymentCriteria, pageNum){
        this.props.dispatch(delPayment(id, paymentCriteria));
        // this.props.onDelete(id);
    }

    render() {

        const columns = [
            {title: '交费时间', dataIndex: 'transferDate', key: 'transferDate'},
            {title: '交费机构名称', dataIndex: 'distributor.name', key: 'distributor.name'},
            {title: '交费人', dataIndex: 'payer', key: 'payer'},
            {title: '产品名称', dataIndex: 'commodity.name', key: 'commodity.name'},
            {title: '金额(元)', dataIndex: 'fee', key: 'fee'},
            {
                title: '操作', dataIndex: '', key: 'x', render: (text, record, index) => (

                <span>
                    {/*<a onClick={()=>this.view(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 查看</a>*/}
                    <a onClick={()=>this.edit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 修改</a>
                    {/*<DelConfirm onOk = {()=>this.delete(record.id, paymentCriteria, paymentList.pageNum)}/>*/}
                </span>

            ),
            },
        ];
        const {paymentList} = this.props;

        const {paymentCriteria} = this.props;

        const pagination = {
            total: paymentList.total,
            pageSize: paymentList.pageSize,
            current: paymentList.pageNum,
            onChange: (page) => {
                this.props.dispatch(refreshPaymentList(paymentCriteria, {"pageNum": page}));
            },
        };

        return (
            <div>
                <Table
                    columns={columns}
                    bordered
                    dataSource={paymentList.list}
                    pagination={pagination}
                    className="table"
                />
            </div>
        )
    }
};
