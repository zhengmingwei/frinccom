import React from 'react';
import {Icon, Table, Modal, Row, Col} from 'antd';
import {refreshDictionaryList2, changeDictionaryStatus, delDictionary} from 'actions/DictionaryActions';
import {showModalDialog} from 'actions/CommonAction';
import DelConfirm from '../common/DelConfirm';

const confirm = Modal.confirm;
/**
 * Dictionary: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class OrderPackageList extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
        this.props.onView(id);
    }

    edit(id){
        console.log(" List2 修改 获取ID********************");
        console.log(id);
        this.props.onEdit(id);
    }

    delete(id, dictionaryCriteria, pageNum){
        this.props.dispatch(delDictionary(id, dictionaryCriteria));
    }

    render() {

        const columns = [
            {title: '名称', dataIndex: 'name', key: 'name'},
            {title: '价格（元）', dataIndex: 'price', key: 'price'},
            {title: '购买总是', dataIndex: 'total', key: 'total'},
            {title: '已用个数', dataIndex: 'quantityUsed', key: 'quantityUsed'},
            {title: '剩余个数', dataIndex: 'surplusQuentity', key: 'surplusQuentity'},
            {title: '起始时间', dataIndex: 'createTime', key: 'createTime'},
            {title: '有效期至', dataIndex: 'endTime', key: 'endTime'},
        ];

        const {dictionaryList2} = this.props;
        const {orderPackageList2} = this.props;

        console.log('********** orderPackageList2 *****************');
        console.log(orderPackageList2);

        const {dictionaryCriteria} = this.props;


        let industrys = new Map();

        dictionaryList2.forEach((item)=>{
            if(item.name != "首次加入费"){
                industrys.set(item.id,<a href={'/incc/alipay/goConfirm.action?productId='+item.id} target ="_blank" style={{color:'#2db7f5',cursor:'pointer'}}> 购买</a>);
            }
        })


        console.log('***************************');
        console.log(industrys);
        console.log('*************************** 我的套餐 !!!!!!!!!!!!!!!!!!!!!');
        return (

            <div>

                <div>

                    <Table
                        columns={columns}
                        bordered
                        dataSource={orderPackageList2}
                        className="table"
                    />

                </div>

            </div>
        )
    }
};
