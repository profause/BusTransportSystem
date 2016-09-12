<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * Destinations Controller
 *
 * @property \App\Model\Table\DestinationsTable $Destinations
 */
class DestinationsController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $destinations = $this->paginate($this->Destinations);

        $this->set(compact('destinations'));
        $this->set('_serialize', ['destinations']);
    }

    /**
     * View method
     *
     * @param string|null $id Destination id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $destination = $this->Destinations->get($id, [
            'contain' => ['Tickets']
        ]);

        $this->set('destination', $destination);
        $this->set('_serialize', ['destination']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $destination = $this->Destinations->newEntity();
        if ($this->request->is('post')) {
            $destination = $this->Destinations->patchEntity($destination, $this->request->data);
            if ($this->Destinations->save($destination)) {
                $this->Flash->success(__('The destination has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The destination could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('destination'));
        $this->set('_serialize', ['destination']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Destination id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $destination = $this->Destinations->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $destination = $this->Destinations->patchEntity($destination, $this->request->data);
            if ($this->Destinations->save($destination)) {
                $this->Flash->success(__('The destination has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The destination could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('destination'));
        $this->set('_serialize', ['destination']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Destination id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $destination = $this->Destinations->get($id);
        if ($this->Destinations->delete($destination)) {
            $this->Flash->success(__('The destination has been deleted.'));
        } else {
            $this->Flash->error(__('The destination could not be deleted. Please, try again.'));
        }

        return $this->redirect(['action' => 'index']);
    }
}
