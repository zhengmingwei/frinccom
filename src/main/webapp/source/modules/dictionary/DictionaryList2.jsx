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
export default class DictionaryList2 extends React.Component {
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
            {title: '产品编号', dataIndex: '', key: 'id', render: (text, record, index) => (

                <span>
                    {index + 1}
                </span>

            )},
            {title: '名称', dataIndex: 'name', key: 'name'},
            {title: '描述说明', dataIndex: 'describe', key: 'describe'},
            {title: '价格（元）', dataIndex: 'price', key: 'price'},
            {title: '个数', dataIndex: 'total', key: 'total'},
            {title: '起始时间', dataIndex: 'createTime', key: 'createTime'},
            {title: '截止时间', dataIndex: 'endTime', key: 'endTime'},
            {
                title: '操作', dataIndex: '', key: 'x', render: (text, record, index) => (

                <span>
                    {/*<a onClick={()=>this.view(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 查看</a>*/}
                    <a onClick={()=>this.edit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 修改</a>
                    <DelConfirm onOk = {()=>this.delete(record.id, dictionaryCriteria, dictionaryList2.pageNum)}/>
                </span>

            ),
            },
        ];
         
        	 const {dictionaryList2} = this.props;
        const {dictionaryCriteria} = this.props;
      

        let industrys = new Map();
        dictionaryList2.forEach((item)=>{
            industrys.set(item.id, item.describe);
        })
        console.log(industrys);

        return (
            <div>
                <Table
                    columns={columns}
                    bordered
                    dataSource={dictionaryList2}
                    className="table"
                />
            </div>
        )
    }
};
