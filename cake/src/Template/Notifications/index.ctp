<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('New Notification'), ['action' => 'add']) ?></li>
    </ul>
</nav>
<div class="notifications index large-9 medium-8 columns content">
    <h3><?= __('Notifications') ?></h3>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th><?= $this->Paginator->sort('id') ?></th>
                <th><?= $this->Paginator->sort('title') ?></th>
                <th><?= $this->Paginator->sort('content') ?></th>
                <th><?= $this->Paginator->sort('createDate') ?></th>
                <th><?= $this->Paginator->sort('updateDate') ?></th>
                <th><?= $this->Paginator->sort('delFlg') ?></th>
                <th class="actions"><?= __('Actions') ?></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($notifications as $notification): ?>
            <tr>
                <td><?= $this->Number->format($notification->id) ?></td>
                <td><?= h($notification->title) ?></td>
                <td><?= h($notification->content) ?></td>
                <td><?= h($notification->createDate) ?></td>
                <td><?= h($notification->updateDate) ?></td>
                <td><?= $this->Number->format($notification->delFlg) ?></td>
                <td class="actions">
                    <?= $this->Html->link(__('View'), ['action' => 'view', $notification->id]) ?>
                    <?= $this->Html->link(__('Edit'), ['action' => 'edit', $notification->id]) ?>
                    <?= $this->Form->postLink(__('Delete'), ['action' => 'delete', $notification->id], ['confirm' => __('Are you sure you want to delete # {0}?', $notification->id)]) ?>
                </td>
            </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    <div class="paginator">
        <ul class="pagination">
            <?= $this->Paginator->prev('< ' . __('previous')) ?>
            <?= $this->Paginator->numbers() ?>
            <?= $this->Paginator->next(__('next') . ' >') ?>
        </ul>
        <p><?= $this->Paginator->counter() ?></p>
    </div>
</div>
