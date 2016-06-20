<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * ItemReplies Controller
 *
 * @property \App\Model\Table\ItemRepliesTable $ItemReplies
 */
class ItemRepliesController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $itemReplies = $this->paginate($this->ItemReplies);

        $this->set(compact('itemReplies'));
        $this->set('_serialize', ['itemReplies']);
    }

    /**
     * View method
     *
     * @param string|null $id Item Reply id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $itemReply = $this->ItemReplies->get($id, [
            'contain' => []
        ]);

        $this->set('itemReply', $itemReply);
        $this->set('_serialize', ['itemReply']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $itemReply = $this->ItemReplies->newEntity();
        $output = [];
        if ($this->request->is('post')) {
            $data = $this->request->data;
            // var_dump($_POST);die();
            $itemReply = $this->ItemReplies->patchEntity($itemReply, $this->request->data);
            if ($this->ItemReplies->save($itemReply)) {
                $this->Flash->success(__('The item has been saved.'));
                $output['result'] = 1;
                // return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The item could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('itemReply'));
        $this->set('_serialize', ['itemReply']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Item Reply id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $itemReply = $this->ItemReplies->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $itemReply = $this->ItemReplies->patchEntity($itemReply, $this->request->data);
            if ($this->ItemReplies->save($itemReply)) {
                $this->Flash->success(__('The item reply has been saved.'));
                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The item reply could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('itemReply'));
        $this->set('_serialize', ['itemReply']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Item Reply id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $itemReply = $this->ItemReplies->get($id);
        if ($this->ItemReplies->delete($itemReply)) {
            $this->Flash->success(__('The item reply has been deleted.'));
        } else {
            $this->Flash->error(__('The item reply could not be deleted. Please, try again.'));
        }
        return $this->redirect(['action' => 'index']);
    }
}
