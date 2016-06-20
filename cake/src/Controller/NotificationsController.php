<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * Notifications Controller
 *
 * @property \App\Model\Table\NotificationsTable $Notifications
 */
class NotificationsController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $this->viewBuilder()->layout('json');

        $data = $this->Notifications->find('all');

        $output = [];

        if ($data) {
            $output['result'] = 1;
            $array = array();

            foreach ($data as $notification) {

                $time = $notification->createDate;
                $time = strtotime($time);
                $posttime = date('Y-m-d' , $time);

                $array[] = [
                    'title'  =>  $notification->title,
                    'content'  =>  $notification->content,
                    'posttime'   =>  $posttime
                ];
            }
            $output['data'] = $array;
            $output['totalCount'] = $data->count();
        } else {
            $output['result'] = 0;
            $output['reason'] = 'không lấy được dữ liệu';
        }
        
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');

    }

    /**
     * View method
     *
     * @param string|null $id Notification id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $notification = $this->Notifications->get($id, [
            'contain' => []
        ]);

        $this->set('notification', $notification);
        $this->set('_serialize', ['notification']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $output = [];
        $notification = $this->Notifications->newEntity();
        if ($this->request->is('post')) {
            $notification = $this->Notifications->patchEntity($notification, $this->request->data);
            if ($this->Notifications->save($notification)) {
                $this->Flash->success(__('The notification has been saved.'));
                $output['result'] = 1;
            } else {
                $this->Flash->error(__('The notification could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('notification'));
        $this->set('_serialize', ['notification']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Notification id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $notification = $this->Notifications->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $notification = $this->Notifications->patchEntity($notification, $this->request->data);
            if ($this->Notifications->save($notification)) {
                $this->Flash->success(__('The notification has been saved.'));
                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The notification could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('notification'));
        $this->set('_serialize', ['notification']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Notification id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $notification = $this->Notifications->get($id);
        if ($this->Notifications->delete($notification)) {
            $this->Flash->success(__('The notification has been deleted.'));
        } else {
            $this->Flash->error(__('The notification could not be deleted. Please, try again.'));
        }
        return $this->redirect(['action' => 'index']);
    }
}
